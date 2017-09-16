package com.tamll.learn.utils;

import java.util.*;

/**
 * Map工具类
 */
public class MapUtils {

    /**
     * hashMap按值排序(降序)
     * @param map 要排序的map
     * @param <K> 键
     * @param <V> 值
     * @return 返回排序完成的HashMap
     */
    public static <K,V extends Comparable<? super V>>Map<K,V> sortByValue(Map<K,V> map){
        List<Map.Entry<K,V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        Map<K,V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K,V> entry:list){
            result.put(entry.getKey(),entry.getValue());
        }
        return result;
    }
}
