package com.bittech.everything.core.model;

import lombok.Data;

/**
 * 文件属性信息索引之后的记录 Thing表示
 * <p>
 * Author: secondriver
 * Created: 2019/2/14
 * Description: 比特科技，只为更好的你；你只管学习，其它交给我。
 */
@Data //getter setter toString生成完成
public class Thing {
    
    /**
     * 文件名称（保留名称）
     * File D:/a/b/hello.txt  -> hello.txt
     */
    private String name;
    
    /**
     * 文件路径
     */
    private String path;
    
    /**
     * 文件路径深度
     */
    private Integer depth;
    
    /**
     * 文件类型
     */
    private FileType fileType;
}
