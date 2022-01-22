package com.yph.redis.service.impl;


import com.yph.redis.serializer.GenericSerializer;
import com.yph.redis.serializer.KeyValueArraySerializer;
import com.yph.redis.serializer.KeyValueSerializer;
import com.yph.redis.service.RedisService;
import com.yph.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void set(String key, Object obj) {
        this.set(key, JSONUtils.toString(obj));
    }

    @Override
    public void set(String key, String str) {
        redisTemplate.opsForValue().set(key, str);
    }

    @Override
    public boolean setIfAbsent(String key, Object obj) {
        return this.setIfAbsent(key, JSONUtils.toString(obj));
    }

    @Override
    public boolean setIfAbsent(String key, String str) {
        Boolean bool = redisTemplate.opsForValue().setIfAbsent(key, str);
        return bool == null ? false : bool;
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> T get(String key, Class<T> type) {
        String value = this.get(key);
        if (type.equals(String.class)) {
            return type.cast(type);
        }
        return JSONUtils.toObject(value, type);
    }

    @Override
    public <T> List<T> getArray(String key, Class<T> type) {
        return JSONUtils.toArray(redisTemplate.opsForValue().get(key), type);
    }

    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }


    @Override
    public Double incr(String key, double delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public void set(String key, String hashKey, Object obj) {
        redisTemplate.opsForHash().put(key, hashKey, JSONUtils.toString(obj));
    }

    @Override
    public String get(String key, String hashKey) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(key, hashKey);
    }
    @Override
    public <T> T get(String key, String hashKey, Class<T> type) {
        return JSONUtils.toObject(this.get(key, hashKey), type);
    }

    @Override
    public <T> List<T> getArray(String key, String hashKey, Class<T> type) {
        Object hashValue = redisTemplate.opsForHash().get(key, hashKey);
        return JSONUtils.toArray(valueOf(hashValue), type);
    }

    @Override
    public Double incr(String key, String hashKey, double delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    @Override
    public Map<String, String> getAll(String key) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        return hashOperations.entries(key);
    }

    @Override
    public void remove(String key, String... hashKey) {
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        Object[] keys = new Object[hashKey.length];
        System.arraycopy(hashKey, 0, keys, 0, hashKey.length);
        hashOperations.delete(key, keys);
    }

    @Override
    public long removeB(String key, String... hashKey) {
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        Object[] keys = new Object[hashKey.length];
        System.arraycopy(hashKey, 0, keys, 0, hashKey.length);
        return hashOperations.delete(key, keys);
    }

    @Override
    public void removeAll(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void rightPush(String key, Object obj) {
        redisTemplate.opsForList().rightPush(key, JSONUtils.toString(obj));
    }

    @Override
    public String rightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    @Override
    public void leftPush(String key, Object obj) {
        redisTemplate.opsForList().leftPush(key, JSONUtils.toString(obj));
    }

    @Override
    public String leftPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public <T> List<T> get(String key, long begin, long end, Class<T> type) {
        List<String> values = redisTemplate.opsForList().range(key, begin, end);
        if (values == null) {
            return new ArrayList<>();
        }
        if (type.getName().equals(String.class.getName())) {
            //noinspection unchecked
            return (List<T>) values;
        }
        return values.stream().map(value -> JSONUtils.toObject(value, type)).collect(Collectors.toList());
    }

    @Override
    public long size(String key) {
        Long size = redisTemplate.opsForValue().size(key);
        return size == null ? 0 : size;
    }

    @Override
    public long add(String key, String ...values) {
        Long updated = redisTemplate.opsForSet().add(key, values);
        return updated == null ? 0 : updated;
    }

    @Override
    public Set<String> members(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public void add(String key, double score, Object obj) {
        redisTemplate.opsForZSet().add(key, JSONUtils.toString(obj), score);
    }

    @Override
    public List<String> range(String key, long begin, long end) {
        Set<String> values = redisTemplate.opsForZSet().range(key, begin, end);
        if (values == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(values);
    }

    @Override
    public <T> List<T> range(String key, long begin, long end, Class<T> type) {
        Set<String> values = redisTemplate.opsForZSet().range(key, begin, end);
        if (values == null) {
            return new ArrayList<>();
        }
        return values.stream().map(value -> JSONUtils.toObject(value, type)).collect(Collectors.toList());
    }

    @Override
    public List<String> rangeByScore(String key, double min, long max) {
        Set<String> values = redisTemplate.opsForZSet().rangeByScore(key, min, max);
        if (values == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(values);
    }

    @Override
    public <T> List<T> rangeByScore(String key, double min, long max, Class<T> type) {
        Set<String> values = redisTemplate.opsForZSet().rangeByScore(key, min, max);
        if (values == null) {
            return new ArrayList<>();
        }
        return values.stream().map(value -> JSONUtils.toObject(value, type)).collect(Collectors.toList());
    }

    @Override
    public String score(String key, double score) {
        Set<String> values = redisTemplate.opsForZSet().rangeByScore(key, score, score);
        if (values == null || values.size() == 0) {
            return null;
        }
        return values.toArray(new String[0])[0];
    }

    @Override
    public <T> T score(String key, double score, Class<T> type) {
        return JSONUtils.toObject(this.score(key, score), type);
    }

    @Override
    public void remove(String key, double min, double max) {
        redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }

    @Override
    public void remove(String key, long begin, long end) {
        redisTemplate.opsForZSet().removeRange(key, begin, end);
    }

    @Override
    public void removeByScores(String key, Double... scores) {
        if (scores == null || scores.length == 0) {
            return;
        }
        Object[] keys = new Object[scores.length];
        System.arraycopy(scores, 0, keys, 0, scores.length);
        redisTemplate.opsForZSet().remove(key, keys);
    }

    @Override
    public Set<String> removeByPattern(String pattern) {
        Set<String> keys = this.getKeysByPattern(pattern);
        redisTemplate.delete(keys);
        return keys;
    }

    @Override
    public Set<String> getKeysByPattern(String pattern) {
//        redisTemplate.keys()
        return redisTemplate.keys(pattern);
    }

    @Override
    public <T> T get(String key, Class<T> type, KeyValueSerializer<T> serializer) {
        String value = redisTemplate.opsForValue().get(key);
        T data;
        if (value == null) {
            data = serializer.get();
            redisTemplate.opsForValue().set(key, JSONUtils.toString(data));
        } else {
            data = JSONUtils.toObject(value, type);
        }
        return data;
    }

    @Override
    public <T> List<T> getArray(String key, Class<T> type, KeyValueArraySerializer<T> serializer) {
        String value = redisTemplate.opsForValue().get(key);
        List<T> data;
        if (value == null) {
            data = serializer.get();
            redisTemplate.opsForValue().set(key, JSONUtils.toString(data));
        } else {
            data = JSONUtils.toArray(value, type);
        }
        return data;
    }

    @Override
    public <T> T get(String key, GenericSerializer<T> serializer) {
        String value = serializer.getCache();
        T data;
        if (value == null) {
            data = serializer.select();
        } else {
            data = serializer.covert(value);
            serializer.toCache(data);
        }
        return data;
    }

    @Override
    public void beginTransaction() {
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.multi();
    }

    @Override
    public void rollback() {
        redisTemplate.discard();
    }

    @Override
    public void commit() {
        redisTemplate.exec();
    }

    @Override
    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    @Override
    public ValueOperations<String, String> getValueOperations() {
        return redisTemplate.opsForValue();
    }

    @Override
    public HashOperations<String, String, String> getHashOperations() {
        return redisTemplate.opsForHash();
    }

    @Override
    public ListOperations<String, String> getListOperations() {
        return redisTemplate.opsForList();
    }

    @Override
    public SetOperations<String, String> getSetOperations() {
        return redisTemplate.opsForSet();
    }

    @Override
    public ZSetOperations<String, String> getZSetOperations() {
        return redisTemplate.opsForZSet();
    }

    private String valueOf(Object obj) {
        return obj == null ? null : obj.toString();
    }

    private boolean isEmpty(Object obj) {
        return obj == null || obj.equals("");
    }
}
