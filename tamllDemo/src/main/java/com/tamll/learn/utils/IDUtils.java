package com.tamll.learn.utils;

import java.util.Random;

/**
 * 各种ID生成工具
 */
public class IDUtils {

    /**
     * 图片名生成
     * @return 返回用当前时间+随机数生成的图片名
     */
    public static String getImageName(){
        long millis = System.currentTimeMillis();
        Random random = new Random();
        int end3 = random.nextInt(999);
        //不足3位前面补0
        String str = millis + String.format("%03d",end3);
        return str;
    }

    /**
     * 获取商品ID
     * @return 返回用当前时间+随机数生成的long
     */
    public static long getProductId(){
        long millis = System.currentTimeMillis();
        Random random = new Random();
        int end2 = random.nextInt(99);
        String str = millis + String.format("%02d",end2);
        long id = new Long(str);
        return id;
    }

    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            System.out.println(getProductId());
        }
    }
}
