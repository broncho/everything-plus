package com.bittech.everything.core.model;

import lombok.Data;

/**
 * Author: secondriver
 * Created: 2019/2/14
 * Description: 比特科技，只为更好的你；你只管学习，其它交给我。
 */
@Data
public class Condition {
    
    private String name;
    
    private String fileType;
    
    private Integer limit;
    
    /**
     * 检索结果的文件信息depth排序规则
     * 1.默认是true -> asc
     * 2.false -> desc
     */
    private Boolean orderByAsc;
}
