package com.example.shiroweb.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

@Component
public class RedisUtil {

    @Autowired
    JedisPool jedisPool;

    private  Jedis getResource(){
        return jedisPool.getResource();
    }

    public void set(byte[] key, byte[] value) {
        Jedis jedis =getResource();
        try{
            jedis.set(key,value);
        }finally {
            jedis.close();
        }
    }

    public void expire(byte[] key, int i){
        Jedis jedis = getResource();
        try{
            jedis.expire(key,i);
        }finally {
            jedis.close();
        }
    }

    public byte[] get(byte[] key) {
        Jedis jedis = getResource();
        try{
            return jedis.get(key);
        }finally {
            jedis.close();
        }
    }

    public void del(byte[] key) {
        Jedis jedis = getResource();
        try{
            jedis.del(key);
        }finally {
            jedis.close();
        }
    }

    public Set<byte[]> getKeys(String shiro_sessiom_prefix) {
        Jedis jedis = getResource();
        try{
            return jedis.keys((shiro_sessiom_prefix+"*").getBytes());
        }finally {
            jedis.close();
        }
    }
}
