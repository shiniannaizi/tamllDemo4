package com.tamll.learn.utils;

import com.tamll.learn.redis.JedisClient;
import com.tamll.learn.redis.JedisClientSingle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * redis操作工具类
 */
@Component
public class RedisUtils {

    @Autowired
    private JedisClient jedisClient;

    private static Logger logger = Logger.getLogger(RedisUtils.class);

    /**
     * 缓存一个对象
     * @param rediesKey 键
     * @param o 对象
     * @param second 缓存时间
     */
    public void setRedies(String rediesKey,Object o,int second){
        try {
            jedisClient.set(rediesKey,o);
            jedisClient.expire(rediesKey,second);
        }catch (Exception e){
            logger.info("写入缓存接口,写入异常,异常信息:"+e);
            e.printStackTrace();
        }
    }

    /**
     * 获取redis缓存中的一个对象
     * @param redisKey 键
     * @return 返回对象
     */
    public Object getRedis(String redisKey){
        Object o = new Object();
        try {
            if (jedisClient.get(redisKey)==null){
                return null;
            }else {
                o = jedisClient.get(redisKey);
            }
        }catch (Exception e){
            logger.info("获取缓存接口,获取异常,异常信息:"+e);
            e.printStackTrace();
        }
        return o;
    }

    /**
     * 删除缓存中的对象
     * @param redisKey 键
     */
    public void delRedis(String redisKey){
        try {
            jedisClient.del(redisKey);
        }catch (Exception e){
            logger.info("缓存删除接口,删除异常,异常信息:"+e);
            e.printStackTrace();
        }
    }

    /**
     * 缓存一个对象的集合
     * @param redisKey 键
     * @param list 集合
     * @param second 缓存时间
     */
    public void setRedisList(String redisKey,List<?> list,int second){
        try {
            jedisClient.setList(redisKey,list);
            jedisClient.expire(redisKey,second);
        }catch (Exception e){
            logger.info("添加列表缓存接口,添加异常,异常信息:"+e);
            e.printStackTrace();
        }
    }

    /**
     * 获取缓存的list集合
     * @param redisKey 键
     * @return 返回一个对象的list
     */
    public List<?> getRedisList(String redisKey){
        List<?> list = new ArrayList();
        try {
            if (jedisClient.getList(redisKey)==null){
                return null;
            }else {
                list = jedisClient.getList(redisKey);
            }
        }catch (Exception e){
            logger.info("获取缓存列表接口,获取异常,异常信息"+e);
            e.printStackTrace();
        }
        return list;
    }
}
