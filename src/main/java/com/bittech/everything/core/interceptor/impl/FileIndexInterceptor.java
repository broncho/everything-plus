package com.bittech.everything.core.interceptor.impl;

import com.bittech.everything.core.common.FileConvertThing;
import com.bittech.everything.core.dao.FileIndexDao;
import com.bittech.everything.core.interceptor.FileInterceptor;
import com.bittech.everything.core.model.Thing;

import java.io.File;

/**
 * Author: secondriver
 * Created: 2019/2/15
 * Description: 比特科技，只为更好的你；你只管学习，其它交给我。
 */
public class FileIndexInterceptor implements FileInterceptor {
    
    private final FileIndexDao fileIndexDao;
    
    public FileIndexInterceptor(FileIndexDao fileIndexDao) {
        this.fileIndexDao = fileIndexDao;
    }
    
    @Override
    public void apply(File file) {
        Thing thing = FileConvertThing.convert(file);
        fileIndexDao.insert(thing);
    }
}
