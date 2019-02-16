package com.bittech.everything.core.index;

import com.bittech.everything.core.interceptor.FileInterceptor;

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
}