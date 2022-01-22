package com.yph.util;

import java.util.*;

/**
 * map操作帮助类
 *
 * @author Agu
 */
public class MapUtil {

    /**
     * 将map里面所有key 生成为sql语句 同时驼峰转下划线
     *
     * @param map
     * @return
     * @throws Exception
     */
    public static String mapToSqlUpper2Line(Map map) throws Exception {
        mapKeySetUpper2Line(map);
        return mapToSql(map);
    }

    /**
     * 将map里面所有key 生成为sql语句 同时下划线转驼峰
     *
     * @param map
     * @return
     * @throws Exception
     */
    public static String mapToSqlLine2Upper(Map map) throws Exception {
        mapKeySetLine2Upper(map);
        return mapToSql(map);
    }


    public static String mapToSqlByLike(Map map, String... likeKeys) {
        List<String> list = new ArrayList();
        for (String likeKey : likeKeys) {
            list.add(likeKey);
        }
        return mapToSql(map, list);
    }

    public static String mapToSql(Map map) {

        return mapToSql(map,new ArrayList<>());
    }

    public static String mapToAddSql(Map map) {
        Set<Map.Entry> set = map.entrySet();
        String sql = "(";
        String valueUrl = "values(";
        for (Map.Entry entry : set) {
            String key = String.valueOf(entry.getKey());
            String value = String.valueOf(entry.getValue());
            sql += "`" + key + "`,";
            valueUrl += "#{" + key + "},";
        }
        sql = sql.substring(0, sql.lastIndexOf(",")) + ")";
        valueUrl = valueUrl.substring(0, valueUrl.lastIndexOf(",")) + ")";
        sql = sql + valueUrl;
        return sql;
    }

    /**
     * map转化为sql 格式:key=#{key}
     *
     * @param map
     * @return
     */
    public static String mapToSql(Map map, List<String> likes) {
        Set<Map.Entry> set = map.entrySet();
        String sql = "";
        String rowIndex = "";
        String limitSql = " order by add_time desc limit";
        boolean isLimit = false;
        String limitTemp = "";
        List<String> list = new ArrayList<>();
        if (set.size() > 0) {
            sql = "where ";
        }
        for (Map.Entry entry : set) {
            String key = String.valueOf(entry.getKey());
            String value = String.valueOf(entry.getValue());
            if(key.equals("page"))
                continue;
            if (key.equals("rowIndex")) {
                isLimit = true;
                rowIndex = " " + value + ",";
                continue;
            }
            if (key.equals("limit")) {
                limitTemp = value;
                continue;
            }
            if (likes.contains(key)) {
                sql += key + " like '%${" + key + "}%' and ";
                continue;
            }
            sql += key + "=#{" + key + "} and ";
        }
        sql = sql.substring(0, sql.lastIndexOf("and"));

        if (isLimit){
            sql += " " + limitSql + rowIndex + limitTemp;
        }
        return sql;
    }


    /**
     * map里面所有key 驼峰转下划线
     *
     * @param map
     * @return
     * @throws Exception
     */
    public static void mapKeySetUpper2Line(Map map) throws Exception {
        Set<Map.Entry> set = map.entrySet();
        Set removeSet = new HashSet();
        Map map1 = new HashMap();
        for (Map.Entry entry : set) {
            String s = Map2JavaBeanUtil.transUpper2UnderLine(entry.getKey().toString());
            if (!entry.getKey().equals(s)) {
                map1.put(s, entry.getValue());
                removeSet.add(entry.getKey());
            }
        }
        map.putAll(map1);
        map.keySet().removeAll(removeSet);
    }




    /**
     * map里面所有key 下划线转驼峰
     *
     * @param map
     * @return
     * @throws Exception
     */
    public static void mapKeySetLine2Upper(Map map) throws Exception {
        Set<Map.Entry> set = map.entrySet();
        Set removeSet = new HashSet();
        Map map1 = new HashMap();
        for (Map.Entry entry : set) {
            String s = Map2JavaBeanUtil.transUnderLine2Upper(entry.getKey().toString());
            if (!entry.getKey().equals(s)) {
                map1.put(s, entry.getValue());
                removeSet.add(entry.getKey());
            }
        }
        map.putAll(map1);
        map.keySet().removeAll(removeSet);
    }

    /**
     * 根据key返回String类型的值
     *
     * @param map
     * @param key
     * @return
     */
    public static String getString(Map map, String key) {
        Object o = map.get(key);
        if (o == null) {
            return null;
        }
        return String.valueOf(o);
    }

    /**
     * 根据key返回int类型的值
     *
     * @param map
     * @param key
     * @return
     */
    public static Integer getInt(Map map, String key) {
        String string = getString(map, key);
        if (string == null||string.trim().length()==0) {
            return null;
        }
        string = string.trim();
        return Integer.parseInt(string);
    }

    /**
     * 根据key返回Double类型的值
     *
     * @param map
     * @param key
     * @return
     */
    public static Double getDouble(Map map, String key) {
        String string = getString(map, key);
        if (string == null||string.trim().length()==0) {
            return null;
        }
        string = string.trim();
        return Double.parseDouble(string);
    }

    /**
     * 根据key返回Long类型的值
     *
     * @param map
     * @param key
     * @return
     */
    public static Long getLong(Map map, String key) {
        String string = getString(map, key);
        if (string == null||string.trim().length()==0) {
            return null;
        }
        string = string.trim();
        return Long.parseLong(string);
    }

    /**
     * 根据key返回Float类型的值
     *
     * @param map
     * @param key
     * @return
     */
    public static Float getFloat(Map map, String key) {
        String string = getString(map, key);
        if (string == null||string.trim().length()==0) {
            return null;
        }
        string = string.trim();
        return Float.parseFloat(string);
    }

}
