package com.tamll.learn.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 序列化文件加载类
 */
public class PropUtils {
    private static Properties prop;
    static{
        prop= new Properties();
        String path = PropUtils.class.getClassLoader().
                getResource("merchantInfo.properties").getPath();
        try {
            prop.load(new FileInputStream(new File(path)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取序列化文件
     * @return 返回序列化对象
     */
    public static Properties getProp(){
        return prop;
    }

    /**
     * 通过键获取值
     * @param key 键
     * @return 返回序列化文件中键对应的值
     */
    public static String getProperty(String key){
        return prop.getProperty(key);
    }
}
