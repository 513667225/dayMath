package com.yph.util;


import com.yph.enun.TipMsgEnum;

import java.util.HashMap;
import java.util.Map;


/**
 * 返回值统一类:R
 *
 *  @author  Agu
 */
public class R extends HashMap {
    public static final int SUCCESS_CODE = 0;
    public static final int ERROR_CODE = 500;
    public static String DATA_KEY = "data";
    public static String CODE_KEY = "code";
    public static String MSG_KEY = "msg";



    private R() {

    }

    public R set(String key, Object object) {
        super.put(key, object);
        return this;
    }


    /**
     * 将当前R里面 key为data的Map转化为Entity 并且在此之前将里面的所有驼峰key转化为下划线key   如: userName-->user_name
     * @param obj 转化的目标entity对象
     * @throws Exception
     */
    public void thisToEntityUpper2Line(Object obj) throws Exception {
        MapUtil.mapKeySetUpper2Line((Map) this.get("data"));
        thisToEntity(obj);
    }

    /**
     * 将当前R key为data的Map转化为Entity 并且在此之前将里面的所有下划线key转化为驼峰key   如: user_name-->userName
     * @param obj 转化的目标entity对象
     * @throws Exception
     */
    public void  thisToEntityLine2Upper(Object obj) throws Exception {
        MapUtil.mapKeySetLine2Upper( (Map) this.get("data"));
        thisToEntity(obj);
    }

    /**
     *  将当前R里面 key为data的Map转化并构建一个Entity 并且在此之前将里面的所有驼峰key转化为下划线key   如: userName-->user_name
     * @param toClass 需要转化的目标entity的class对象
     * @param <T> 目标entity类型泛型
     * @return 构建出来的entity
     * @throws Exception
     */
    public <T> T thisToEntityUpper2Line(Class<T> toClass) throws Exception {
//        this.keySet()
        MapUtil.mapKeySetUpper2Line((Map) this.get("data"));
        return thisToEntity(toClass);
    }


    /**
     * 将当前R key为data的Map转化并构建一个Entity 并且在此之前将里面的所有下划线key转化为驼峰key   如: user_name-->userName
     * @param toClass 需要转化的目标entity的class对象
     * @param <T> 目标entity类型泛型
     * @return 构建出来的entity
     * @throws Exception
     */
    public <T> T thisToEntityLine2Upper(Class<T> toClass) throws Exception {
        MapUtil.mapKeySetLine2Upper((Map) this.get("data"));
        return thisToEntity(toClass);
    }


    /**
     * 将当前R key为data的Map转化并构建一个Entity
     * @param toClass 需要转化的目标entity的class对象
     * @param <T> 目标entity类型泛型
     * @return 构建出来的entity
     * @throws Exception
     */
    public <T> T thisToEntity(Class<T> toClass) throws Exception {

        return (T) Map2JavaBeanUtil.transMap2Bean((Map) this.get("data"), toClass);
    }

    /**
     * 将当前R key为data的Map转化为Entity
     * @param toObj 转化的目标entity对象
     * @throws Exception
     */
    public void thisToEntity(Object toObj) throws Exception {
    if (this.get("data")!=null)
        Map2JavaBeanUtil.transMap2Bean((Map) this.get("data"), toObj);
    }


    public static R ok() {
        return new R();
    }

    public static R success() {

        return R.ok().set(CODE_KEY, R.SUCCESS_CODE).set(R.MSG_KEY, "操作成功");
    }

    public static R success(String msg) {

        return R.ok().set(CODE_KEY, R.SUCCESS_CODE).set(R.MSG_KEY, msg);
    }

    public static R success(String msg, Object object) {

        return R.ok().set(CODE_KEY, R.SUCCESS_CODE).set(R.MSG_KEY, msg).set(R.DATA_KEY, object);
    }

    public R data(Object obj) {
        return this.set("data", obj);
    }

    public static R error() {
        return R.ok().set(R.MSG_KEY, "操作失败").set(CODE_KEY, R.ERROR_CODE);
    }

    public static R error(String msg) {
        return R.ok().set(R.MSG_KEY, msg).set(CODE_KEY, R.ERROR_CODE);
    }

    public static R error(String msg, Object object) {
        return R.ok().set(R.MSG_KEY, msg).set(R.DATA_KEY, object).set(CODE_KEY, R.ERROR_CODE);
    }

    public static R error(TipMsgEnum tipMsgEnum) {
        return R.ok().set(R.MSG_KEY, tipMsgEnum.getMsg()).set(CODE_KEY, tipMsgEnum.getCode());
    }

    public static R error(TipMsgEnum tipMsgEnum, Object object) {
        return R.ok().set(R.MSG_KEY, tipMsgEnum.getMsg()).set(R.DATA_KEY, object).set(CODE_KEY, tipMsgEnum.getCode());
    }

}
