package com.example.shiroweb.config;

import com.example.shiroweb.redis.RedisUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

@Component
public class RedisCache<K,V> implements Cache<K,V> {

    @Resource
    RedisUtil redisUtil;

    private final String CACHE_PREFIX = "redis-cache";

    private byte[] getKey(K k){
     if(k instanceof String){
         return (CACHE_PREFIX+k).getBytes();
     }
     return SerializationUtils.serialize(k);
    }

    @Override
    public V get(K key) throws CacheException {
        System.out.println("从redis中取数据");
        byte[] value = redisUtil.get(getKey(key));
        if(value != null){
            return (V)SerializationUtils.deserialize(value);
        }
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        byte[] key = getKey(k);
        byte[] value = SerializationUtils.serialize(v);
        redisUtil.set(key,value);
        redisUtil.expire(key,600);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        byte[] key =  getKey(k);
        byte[] value = redisUtil.get(key);
        redisUtil.del(key);
        if(value != null ){
            return (V)SerializationUtils.deserialize(value);
        }
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
