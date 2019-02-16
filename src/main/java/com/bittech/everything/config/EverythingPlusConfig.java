package com.bittech.everything.config;

import lombok.Getter;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: secondriver
 * Created: 2019/2/15
 * Description: 比特科技，只为更好的你；你只管学习，其它交给我。
 */
@Getter
public class EverythingPlusConfig {
    
    private static volatile EverythingPlusConfig config;
    
    /**
     * 建立索引的路径
     */
    private Set<String> includePath = new HashSet<>();
    /**
     * 排除索引文件的路径
     */
    private Set<String> excludePath = new HashSet<>();
    
    
    //TODO 可配置的参数会在这里体现
    
    /**
     * H2数据库文件路径
     */
    private String h2IndexPath = System.getProperty("user.dir") + File.separator + "everything_plus";
    
    private EverythingPlusConfig() {
    
    }
    
    private void initDefaultPathsConfig() {
        //1.获取文件系统
        FileSystem fileSystem = FileSystems.getDefault();
        //遍历的目录
        Iterable<Path> iterable = fileSystem.getRootDirectories();
        iterable.forEach(path -> config.includePath.add(path.toString()));
        //排除的目录
        //windows ： C:\Windows C:\Program Files (x86) C:\Program Files  C:\ProgramData
        //linux : /tmp /etc
        //unix
        String osname = System.getProperty("os.name");
        if (osname.startsWith("Windows")) {
            config.getExcludePath().add("C:\\Windows");
            config.getExcludePath().add("C:\\Program Files (x86)");
            config.getExcludePath().add("C:\\Program Files");
            config.getExcludePath().add("C:\\ProgramData");
            
        } else {
            config.getExcludePath().add("/tmp");
            config.getExcludePath().add("/etc");
            config.getExcludePath().add("/root");
        }
    }
    
    public static EverythingPlusConfig getInstance() {
        if (config == null) {
            synchronized(EverythingPlusConfig.class) {
                if (config == null) {
                    config = new EverythingPlusConfig();
                    config.initDefaultPathsConfig();
                }
            }
        }
        return config;
    }
}
