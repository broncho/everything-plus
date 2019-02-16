package com.bittech.everything.core.interceptor;

import com.bittech.everything.core.model.Thing;

/**
 * Author: secondriver
 * Created: 2019/2/16
 * Description: 比特科技，只为更好的你；你只管学习，其它交给我。
 */
@FunctionalInterface
public interface ThingInterceptor {
    
    void apply(Thing thing);
}
