package com.bittech.everything.core.interceptor.impl;

import com.bittech.everything.core.dao.FileIndexDao;
import com.bittech.everything.core.interceptor.ThingInterceptor;
import com.bittech.everything.core.model.Thing;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Author: secondriver
 * Created: 2019/2/16
 * Description: 比特科技，只为更好的你；你只管学习，其它交给我。
 */
public class ThingClearInterceptor implements ThingInterceptor, Runnable {
    
    private Queue<Thing> queue = new ArrayBlockingQueue<>(1024);
    
    private final FileIndexDao fileIndexDao;
    
    public ThingClearInterceptor(FileIndexDao fileIndexDao) {
        this.fileIndexDao = fileIndexDao;
    }
    
    @Override
    public void apply(Thing thing) {
        this.queue.add(thing);
    }
    
    @Override
    public void run() {
        while (true) {
            Thing thing = this.queue.poll();
            if (thing != null) {
                fileIndexDao.delete(thing);
            }
            //1.优化 批量删除
            //List<Thing> thingList = new ArrayList<>();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
