package com.bittech.everything.core.dao;

import com.bittech.everything.core.model.Condition;
import com.bittech.everything.core.model.Thing;

import java.util.List;

/**
 * 业务层访问数据库的CRUD
 * <p>
 * Author: secondriver
 * Created: 2019/2/15
 * Description: 比特科技，只为更好的你；你只管学习，其它交给我。
 */
public interface FileIndexDao {
    
    /**
     * 插入数据Thing
     *
     * @param thing
     */
    void insert(Thing thing);
    
    /**
     * 根据condition条件进行数据库的检索
     *
     * @param condition
     * @return
     */
    List<Thing> search(Condition condition);
}
