package com.bittech.everything.core.monitor;

import com.bittech.everything.core.common.HandlePath;

/**
 * Author: secondriver
 * Created: 2019/2/17
 * Description: 比特科技，只为更好的你；你只管学习，其它交给我。
 */
public interface FileWatch {
    
    /**
     * 监听启动
     */
    void start();
    
    /**
     * 监听的目录
     */
    void monitor(HandlePath handlePath);
    
    /**
     * 监听停止
     */
    void stop();
}
