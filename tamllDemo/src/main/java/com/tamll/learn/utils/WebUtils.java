package com.tamll.learn.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 基础工具类
 */
public class WebUtils {
    private WebUtils(){}

    /**
     * 检查字符串是否为空
     * @param str
     * @return boolean true表示字符串为空或""
     */
    public static boolean isNull(String str){
        return str == null || "".equals(str.trim());
    }

    public static String md5(String plainText){
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("没有md5这个算法");
        }
        String md5code = new BigInteger(1,secretBytes).toString(16);
        for (int i = 0;i < 32-md5code.length();i++){
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
