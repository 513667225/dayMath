package com.yph.util.utli;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtli {
    public static Map<String,Object> StringToMap(String key,Object value){
        Map<String, Object> stringObjectMap = new HashMap<>();
        if(value instanceof Integer){
            stringObjectMap.put(key,Integer.valueOf(String.valueOf(value)));
        }else{
            stringObjectMap.put(key,value);
        }
        return stringObjectMap;
    }

    public static Map<String,Object> StringToMap(String key,Object value,String key2,Object value2){
        Map<String, Object> stringObjectMap = new HashMap<>();
        if(value instanceof Integer){
            stringObjectMap.put(key,Integer.valueOf(String.valueOf(value)));
        }else{
            stringObjectMap.put(key,value);
        }
        if(value2 instanceof Integer){
            stringObjectMap.put(key2,Integer.valueOf(String.valueOf(value)));
        }else{
            stringObjectMap.put(key2,value2);
        }
        return stringObjectMap;
    }

    public static Map<String,Object> NullToMap(){
        Map<String, Object> stringObjectMap = new HashMap<>();
        return stringObjectMap;
    }
}
