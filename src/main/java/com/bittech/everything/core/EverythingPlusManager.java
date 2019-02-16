package com.bittech.everything.core;

import com.bittech.everything.config.EverythingPlusConfig;
import com.bittech.everything.core.dao.DataSourceFactory;
import com.bittech.everything.core.dao.FileIndexDao;
import com.bittech.everything.core.dao.impl.FileIndexDaoImpl;
import com.bittech.everything.core.index.FileScan;
import com.bittech.everything.core.index.impl.FileScanImpl;
import com.bittech.everything.core.model.Condition;
import com.bittech.everything.core.model.Thing;
import com.bittech.everything.core.search.FileSearch;
import com.bittech.everything.core.search.impl.FileSearchImpl;

import javax.sql.DataSource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: secondriver
 * Created: 2019/2/16
 * Description: 比特科技，只为更好的你；你只管学习，其它交给我。
 */
public final class EverythingPlusManager {
    
    private static volatile EverythingPlusManager manager;
    
    private FileSearch fileSearch;
    
    private FileScan fileScan;
    
    private ExecutorService executorService;
    
    private EverythingPlusManager() {
        this.initComponent();
    }
    
    private void initComponent() {
        //数据源对象
        DataSource dataSource = DataSourceFactory.dataSource();
        //业务层的对象
        FileIndexDao fileIndexDao = new FileIndexDaoImpl(dataSource);
        this.fileSearch = new FileSearchImpl(fileIndexDao);
        this.fileScan = new FileScanImpl();
    }
    
    public static EverythingPlusManager getInstance() {
        if (manager == null) {
            synchronized(EverythingPlusManager.class) {
                if (manager == null) {
                    manager = new EverythingPlusManager();
                }
            }
        }
        return manager;
    }
    
    
    /**
     * 检索
     */
    public List<Thing> search(Condition condition) {
        //NOTICE 扩展
        return this.fileSearch.search(condition);
    }
    
    /**
     * 索引
     */
    public void buildIndex() {
        Set<String> directories = EverythingPlusConfig.getInstance().getIncludePath();
        if (this.executorService == null) {
            this.executorService = Executors.newFixedThreadPool(directories.size(), new ThreadFactory() {
                private final AtomicInteger threadId = new AtomicInteger(0);
                
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setName("Thread-Scan-" + threadId.getAndIncrement());
                    return thread;
                }
            });
        }
        final CountDownLatch countDownLatch = new CountDownLatch(directories.size());
        System.out.println("Build index start ....");
        for (String path : directories) {
            this.executorService.submit(new Runnable() {
                @Override
                public void run() {
                    EverythingPlusManager.this.fileScan.index(path);
                    //当前任务完成，值-1
                    countDownLatch.countDown();
                }
            });
        }
        
        /**
         * 阻塞，直到任务完成，值0
         */
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Build index complete ...");
    }
}

