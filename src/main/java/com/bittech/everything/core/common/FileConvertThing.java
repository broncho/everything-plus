package com.bittech.everything.core.common;

import com.bittech.everything.core.model.FileType;
import com.bittech.everything.core.model.Thing;

import java.io.File;

/**
 * 辅助工具类：将File对象转换成Thing对象
 * <p>
 * Author: secondriver
 * Created: 2019/2/15
 * Description: 比特科技，只为更好的你；你只管学习，其它交给我。
 */
public final class FileConvertThing {
    
    private FileConvertThing() {
    }
    
    public static Thing convert(File file) {
        Thing thing = new Thing();
        thing.setName(file.getName());
        thing.setPath(file.getAbsolutePath());
        thing.setDepth(computeFileDepth(file));
        thing.setFileType(computeFileType(file));
        return thing;
    }
    
    private static int computeFileDepth(File file) {
        int dept = 0;
        String[] segments = file.getAbsolutePath().split("\\\\");
        dept = segments.length;
        return dept;
    }
    
    public static void main(String[] args) {
        System.out.println(computeFileDepth(new File("D:\\a\\b\\c.txt")));
        System.out.println(computeFileDepth(new File("D:\\a\\c.txt")));
    }
    
    private static FileType computeFileType(File file) {
        if (file.isDirectory()) {
            return FileType.OTHER;
        }
        String fileName = file.getName();
        int index = fileName.lastIndexOf(".");
        if (index != -1 && index < fileName.length() - 1) {
            //abc.
            String extend = fileName.substring(index + 1);
            return FileType.lookup(extend);
        } else {
            return FileType.OTHER;
        }
    }
}
