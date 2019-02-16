package com.bittech.everything.core.search.impl;

import com.bittech.everything.core.dao.FileIndexDao;
import com.bittech.everything.core.model.Condition;
import com.bittech.everything.core.model.Thing;
import com.bittech.everything.core.search.FileSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务层
 * Author: secondriver
 * Created: 2019/2/15
 * Description: 比特科技，只为更好的你；你只管学习，其它交给我。
 */
public class FileSearchImpl implements FileSearch {
    
    private final FileIndexDao fileIndexDao;
    
    public FileSearchImpl(FileIndexDao fileIndexDao) {
        this.fileIndexDao = fileIndexDao;
    }
    
    @Override
    public List<Thing> search(Condition condition) {
        if (condition == null) {
            return new ArrayList<>();
        }
        return this.fileIndexDao.search(condition);
    }
}