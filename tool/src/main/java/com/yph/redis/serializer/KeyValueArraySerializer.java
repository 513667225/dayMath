package com.yph.redis.serializer;

import java.util.List;

public interface KeyValueArraySerializer<T> {
    List<T> get();
}