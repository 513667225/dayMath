package com.yph.redis.service;

import com.yph.redis.serializer.GenericSerializer;
import com.yph.redis.serializer.KeyValueArraySerializer;
import com.yph.redis.serializer.KeyValueSerializer;
import org.springframework.data.redis.core.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisService {

    // key-value

    void set(String key, Object obj);

    void set(String key, String str);

    boolean setIfAbsent(String key, Object obj);

    boolean setIfAbsent(String key, String str);

    String get(String key);

    <T> T get(String key, Class<T> type);

    <T> List<T> getArray(String key, Class<T> type);

    void remove(String key);


    Double incr(String key, double delta);

    // Hash

    void set(String key, String hashKey, Object obj);

    String get(String key, String hashKey);

    <T> T get(String key, String hashKey, Class<T> type);

    <T> List<T> getArray(String key, String hashKey, Class<T> type);

    Double incr(String key, String hashKey, double delta);

    // TODO 需要整改
    Map<String, String> getAll(String key);

    void remove(String key, String ...hashKey);

    long removeB(String key,String ...hashKey);

    void removeAll(String key);

    // List

    void rightPush(String key, Object obj);

    String rightPop(String key);

    void leftPush(String key, Object obj);

    String leftPop(String key);

    <T> List<T> get(String key, long begin, long end, Class<T> type);

    long size(String key);


    // Set

    long add(String key, String ...values);

    Set<String> members(String key);

    // ZSet

    void add(String key, double score, Object obj);

    List<String> range(String key, long begin, long end);

    <T> List<T> range(String key, long begin, long end, Class<T> type);

    List<String> rangeByScore(String key, double min, long max);

    <T> List<T> rangeByScore(String key, double min, long max, Class<T> type);

    String score(String key, double score);

    <T> T score(String key, double score, Class<T> type);

    void remove(String key, double min, double max);

    void remove(String key, long begin, long end);

    void removeByScores(String key, Double ...scores);

    // expand

    Set<String> removeByPattern(String pattern);

    Set<String> getKeysByPattern(String pattern);

    <T> T get(String key, Class<T> type, KeyValueSerializer<T> serializer);

    <T> List<T> getArray(String key, Class<T> type, KeyValueArraySerializer<T> serializer);

    <T> T get(String key, GenericSerializer<T> serializer);

    void beginTransaction();

    void rollback();

    void commit();

    StringRedisTemplate getRedisTemplate();

    ValueOperations<String, String> getValueOperations();

    HashOperations<String, String, String> getHashOperations();

    ListOperations<String, String> getListOperations();

    SetOperations<String, String> getSetOperations();

    ZSetOperations<String, String> getZSetOperations();

}
