package com.tamll.learn.redis;

import java.util.List;

/**
 * redis操作接口
 */
public interface JedisClient {

    Object get(String key);

    String set(String key, Object o);

    void setList(String key, List<?> list);

    List<?> getList(String key);

    String hget(String hkey, String key);

    long hset(String hkey, String key, String value);

    long incr(String key);

    long expire(String key, int second);

    long ttl(String key);

    long del(String key);

    long hdel(String hkey, String key);

}
