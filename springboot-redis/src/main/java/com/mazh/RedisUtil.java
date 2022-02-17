package com.mazh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate = null;

    public boolean delete(String key) {
        return this.redisTemplate.delete(key);
    }

    public boolean isExist(String key) {
        return this.redisTemplate.hasKey(key);
    }

    public void set(String key, String value) {
        this.redisTemplate.opsForValue().set(key, value);
    }

    public Object get(String key) {
        return this.redisTemplate.opsForValue().get(key);
    }

    public Set smembers(String key) {
        return this.redisTemplate.opsForSet().members(key);
    }

    public void sadd(String key, String[] values) {
        this.redisTemplate.opsForSet().add(key, values);
    }

    public void sadd(String key, String value) {
        this.redisTemplate.opsForSet().add(key, value);
    }

    public Object spop(String key) {
        return this.redisTemplate.opsForSet().pop(key);
    }
}
