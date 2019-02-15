package com.bittech.everything.core.index;

import com.bittech.everything.core.dao.DataSourceFactory;
import com.bittech.everything.core.dao.impl.FileIndexDaoImpl;
import com.bittech.everything.core.index.impl.FileScanImpl;
import com.bittech.everything.core.interceptor.FileInterceptor;
import com.bittech.everything.core.interceptor.impl.FileIndexInterceptor;
import com.bittech.everything.core.interceptor.impl.FilePrintInterceptor;

/**
 * Author: secondriver
 * Created: 2019/2/15
 * Description: 比特科技，只为更好的你；你只管学习，其它交给我。
 */
public interface FileScan {
    
    /**
     * 遍历Path
     *
     * @param path
     */
    void index(String path);
    
    /**
     * 遍历的拦截器
     *
     * @param interceptor
     */
    void interceptor(FileInterceptor interceptor);
    
    
    public static void main(String[] args) {
        DataSourceFactory.initDatabase();
        FileScan scan = new FileScanImpl();
        FileInterceptor printInterceptor = new FilePrintInterceptor();
        scan.interceptor(printInterceptor);
        
        FileInterceptor fileIndexInterceptor = new FileIndexInterceptor(new FileIndexDaoImpl(DataSourceFactory.dataSource()));
        scan.interceptor(fileIndexInterceptor);
        
        scan.index("D:\\test");
        
    }
}
