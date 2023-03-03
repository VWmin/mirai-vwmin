package com.vwmin.miraivwmin.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author vwmin
 * @version 1.0
 * @date 2020/4/16 23:25
 */
//@Component
public class RedisUtil {

    private static RedisTemplate<String, Object> redisTemplate;
    private final RedisTemplate<String, Object> autoTemplate;

    public RedisUtil(RedisTemplate<String, Object> autoRedisTemplate) {
        this.autoTemplate = autoRedisTemplate;
    }

    @PostConstruct
    private void init(){
        RedisUtil.redisTemplate = this.autoTemplate;
    }

    public static void add2Set(String cache, String key, Object... values) {
        redisTemplate.opsForSet()
                .add(realKey(cache, key), values);
    }

    public static Boolean isMember(String cache, String key, Object o) {
        return redisTemplate.opsForSet()
                .isMember(realKey(cache, key), o);
    }

    public static Set<Object> members(String cache, String key){
        return redisTemplate.opsForSet()
                .members(realKey(cache, key));
    }

    public static void removeMember(String cache, String key, Object... values){
        redisTemplate.opsForSet()
                .remove(realKey(cache, key), values);
    }

    public static void increase(String cacheName, String key){
        redisTemplate.opsForValue()
                .increment(realKey(cacheName, key));
    }

    public static void put(String cache, String key, Object value){
        redisTemplate.opsForValue()
                .set(realKey(cache, key), value);
    }

    public static void put(String cache, String key, Object value, long ttlSeconds) {
        redisTemplate.opsForValue()
                .set(realKey(cache, key), value, ttlSeconds, TimeUnit.SECONDS);
    }

    public static Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public static Object get(String cacheName, String key ) {
        return get(realKey(cacheName, key));
    }

    public static Boolean remove(String cache, String key){
        return redisTemplate.delete(realKey(cache, key));
    }

    public static void setToZero(String cacheName, String key){
        redisTemplate.opsForValue().set(realKey(cacheName, key), 0);
    }

    public static Map<String, Object> getCache(String cacheName){
        Set<String> keys = redisTemplate.keys(cacheName + "*");
        Map<String, Object> map = new HashMap<>();
        assert keys != null;
        keys.forEach((keyWithCacheName)->{
            String[] split = keyWithCacheName.split(":");
            String key = split[split.length-1];
            map.put(key, get(keyWithCacheName));
        });
        return map;
    }

    private static String realKey(String cache, String key){
        return cache + ":" + key;
    }
}
