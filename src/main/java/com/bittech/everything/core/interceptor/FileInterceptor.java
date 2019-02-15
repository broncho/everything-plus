package com.bittech.everything.core.interceptor;

import java.io.File;

/**
 * Author: secondriver
 * Created: 2019/2/15
 * Description: 比特科技，只为更好的你；你只管学习，其它交给我。
 */
@FunctionalInterface
public interface FileInterceptor {

    void apply(File file);
}
