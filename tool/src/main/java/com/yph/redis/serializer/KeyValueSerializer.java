package com.yph.redis.serializer;

public interface KeyValueSerializer<T> {
    T get();
}