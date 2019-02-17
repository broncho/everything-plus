package com.bittech.everything.core.common;

import lombok.Data;

import java.util.Set;

/**
 * Author: secondriver
 * Created: 2019/2/17
 * Description: 比特科技，只为更好的你；你只管学习，其它交给我。
 */
@Data
public class HandlePath {
    private Set<String> includePath;
    private Set<String> excludePath;
}
