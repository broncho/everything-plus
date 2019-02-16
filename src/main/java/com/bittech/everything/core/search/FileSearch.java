package com.bittech.everything.core.search;

import com.bittech.everything.core.model.Condition;
import com.bittech.everything.core.model.Thing;

import java.util.List;

/**
 * Author: secondriver
 * Created: 2019/2/15
 * Description: 比特科技，只为更好的你；你只管学习，其它交给我。
 */
public interface FileSearch {
    
    /**
     * 根据condition条件进行数据库的检索
     *
     * @param condition
     * @return
     */
    List<Thing> search(Condition condition);
    
}
