package com.tamll.learn.utils;

import java.util.Calendar;

/**
 * 时间工具类
 */
public class TimeUtils {
    public String getTime() {
        Calendar calendar = Calendar.getInstance();
        Long date = calendar.getTime().getTime();

        return date.toString();
    }

    public boolean cmpTime(String time){
        long tempTime = Long.parseLong(time);

        Calendar calendar = Calendar.getInstance();
        Long date = calendar.getTime().getTime();

        if(date - tempTime > 600000){//大于10分钟
            return false;
        }else {
            return true;
        }
    }
}
