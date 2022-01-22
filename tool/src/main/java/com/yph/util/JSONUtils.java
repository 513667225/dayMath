package com.yph.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.YearDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.YearSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * modified by 2020-07-21
 * @author xiaojunbin
 */
public class JSONUtils {

    private static final ObjectMapper DEF_MAPPER = buildMapperWithDefaultConfigure(null);;

    public static void configureDefaultMapper(MapperConfigure mapperConfigure) {
        mapperConfigure.configure(DEF_MAPPER);
    }

    public static ObjectMapper buildMapperWithDefaultConfigure(MapperConfigure mapperConfigure) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        objectMapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        module.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        module.addSerializer(Year.class, new YearSerializer(DateTimeFormatter.ofPattern("yyyy")));

        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        module.addDeserializer(Year.class, new YearDeserializer(DateTimeFormatter.ofPattern("yyyy")));

        objectMapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        objectMapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
        objectMapper.registerModule(module);
        if (mapperConfigure != null) {
            mapperConfigure.configure(objectMapper);
        }
        return objectMapper;
    }

    public static String toString(Object obj) {
        return toString(obj, DEF_MAPPER);
    }

    public static String toString(Object obj, ObjectMapper mapper) {
        String toString = null;
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return ((String) obj);
        }
        try {
            toString = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toString;
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        return toObject(json, clazz, DEF_MAPPER);
    }

    public static <T> T toObject(String json, Class<T> clazz, ObjectMapper mapper) {
        T obj = null;
        if (json == null) {
            return null;
        }
        try {
            obj = mapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static <T> T toObjectWith(String json, Class<T> clazz, Class<?>... args) {
        return toObjectWith(json, clazz, DEF_MAPPER, args);
    }

    public static <T> T toObjectWith(String json, Class<T> clazz, ObjectMapper mapper, Class<?>... args) {
        T obj = null;
        if (json == null) {
            return null;
        }
        try {
            obj = DEF_MAPPER.readValue(json, mapper.getTypeFactory().constructParametricType(clazz, args));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }


    public static <T> List<T> toArray(String json, Class<T> clazz) {
        return toArray(json, clazz, DEF_MAPPER);
    }

    public static <T> List<T> toArray(String json, Class<T> clazz, ObjectMapper mapper) {
        List<T> obj = null;
        if (json == null) {
            return null;
        }
        try {
            obj = mapper.readValue(json, DEF_MAPPER.getTypeFactory().constructParametricType(List.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static <T> Map<String, Object> toMap(String json, Class<? extends Map> mapClazz) {
        return toMap(json, mapClazz, String.class, Object.class, DEF_MAPPER);
    }

    public static <T> Map<String, Object> toMap(String json, Class<? extends Map> mapClazz, ObjectMapper mapper) {
        return toMap(json, mapClazz, String.class, Object.class, mapper);
    }

    public static Map<String, Object> toMap(String json) {
        return toMap(json, Map.class);
    }

    public static Map<String, Object> toMap(String json, ObjectMapper mapper) {
        return toMap(json, Map.class, mapper);
    }

    public static <key, value> Map<key, value> toMap(String json, Class<? extends Map> mapClazz, Class<key> keyClass, Class<value> valueClass, ObjectMapper mapper) {
        Map<key, value> obj = null;
        if (json == null) {
            return null;
        }
        try {
            obj = mapper.readValue(json, mapper.getTypeFactory().constructParametricType(mapClazz, keyClass, valueClass));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static Map<String, Object> mergeAsMap(Object... args) {
        return mergeAsMap(DEF_MAPPER, args);
    }

    public static Map<String, Object> mergeAsMap(ObjectMapper mapper, Object... args) {
        Map<String, Object> result = new HashMap<>();
        for (Object arg : args) {
            result.putAll(toMap(toString(arg), HashMap.class, String.class, Object.class, mapper));
        }
        return result;
    }

    public interface MapperConfigure {

        void configure(ObjectMapper mapper);

    }

    public static <T> T cast(Object obj, Class<T> type) {
        return toObject(toString(obj), type);
    }

}
