package com.tamll.learn.redis;

import com.tamll.learn.utils.SerializeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * 单机版redis操作实例类
 */
public class JedisClientSingle implements JedisClient{

    @Autowired
    private JedisPool jedisPool;

//    String ip = "127.0.0.1";
//    int port = 6379;
//    JedisPool jedisPool = new JedisPool(ip,port);

    @Override
    public Object get(String key) {
        Jedis jedis = jedisPool.getResource();
        byte[] bytes = jedis.get(key.getBytes());
        Object o = SerializeUtils.unserialize(bytes);
        jedis.close();
        return o;
    }

    @Override
    public String set(String key, Object o) {
        Jedis jedis = jedisPool.getResource();
        String string = jedis.set(key.getBytes(), SerializeUtils.serialize(o));
        jedis.close();
        return string;
    }

    @Override
    public void setList(String key, List<?> list) {
        Jedis jedis = jedisPool.getResource();
        try {
            if(list!=null && !list.isEmpty()){
                jedis.set(key.getBytes(), SerializeUtils.serializeList(list));
            }else{//如果list为空,则设置一个空
                jedis.set(key.getBytes(), "".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<?> getList(String key) {
        Jedis jedis = jedisPool.getResource();
        byte[] bytes = jedis.get(key.getBytes());
        return SerializeUtils.unserializeList(bytes);
    }

    @Override
    public String hget(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        String string = jedis.hget(hkey, key);
        jedis.close();
        return string;
    }

    @Override
    public long hset(String hkey, String key, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hset(hkey, key, value);
        jedis.close();
        return result;
    }

    @Override
    public long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    @Override
    public long expire(String key, int second) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.expire(key.getBytes(), second);
        jedis.close();
        return result;
    }

    @Override
    public long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.ttl(key.getBytes());
        jedis.close();
        return result;
    }

    @Override
    public long del(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.del(key.getBytes());
        jedis.close();
        return result;
    }

    @Override
    public long hdel(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hdel(hkey, key);
        jedis.close();
        return result;
    }

}
