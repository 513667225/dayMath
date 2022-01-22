package com.yph.util;

import java.io.Serializable;

/**
 * @author  nachuan
 * 判断 String int  Collection 是否为空
 */
public class EmptyUtil implements Serializable {
    /**
     * 如果参数为null，返回true
     * * @author hechenglong
     * @param patterns
     * @return
     */
    public static boolean isNull(Object... patterns){
        if(patterns == null || patterns.length == 0) return true;
        for (Object obj : patterns) {
            if(obj == null || "".equals(obj) || "".equals(obj.toString().trim()) || "null".equalsIgnoreCase(obj.toString())){
                return true;
            }
        }
        return false;
    }
    /**
     * 如果参数不为null，返回true
     * @author hechenglong
     * @param patterns
     * @return
     */
    public static boolean notNull(Object... patterns) {
        return !isNull(patterns);
    }



}
