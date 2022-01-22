package com.yph.util;

import com.yph.entity.UserDo;
import com.yph.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 入参操作类:P
 * @author Agu
 */
public class P extends HashMap<String,Object> {

    private HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    private HttpServletResponse response;

    private UserEntity userDo;

    public P() {

    }

    public UserEntity getUserDo() throws Exception {
        if (userDo==null){
            throw  new Exception("登录过期，请刷新页面或退出页面重试");
        }
        return userDo;
    }

    public void setUserDo(UserEntity userDo) {
        this.userDo = userDo;
    }

    public P(Map<String,Object> map) {
        super(map);
    }

    /**
     *  参考 @see R  里面的同方法
     *  区别： 此方法是将自己本身转化
     * @param obj
     * @throws Exception
     */
    public void thisToEntityUpper2Line(Object obj) throws Exception {
        thisToEntity(obj,true,false);
    }


    public  void removeNullString(){
        Iterator<Entry<String, Object>> iterator = this.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, Object> next = iterator.next();
            if (next.getValue()==null||String.valueOf(next.getValue()).equals("")) {
                iterator.remove();
            }
        }
    }

    /**
     *  参考 @see R  里面的同方法
     *  区别： 此方法是将自己本身转化
     * @param obj
     * @throws Exception
     */
    public void  thisToEntityLine2Upper(Object obj) throws Exception {
        MapUtil.mapKeySetLine2Upper( this);
        thisToEntity(obj);
    }



    /**
     *  参考 @see R  里面的同方法
     *  区别： 此方法是将自己本身转化
     * @param toClass
     * @throws Exception
     */
    public <T> T thisToEntityUpper2Line(Class<T> toClass) throws Exception {
        T t = toClass.newInstance();
        thisToEntity(t,true,false);
        return t;
    }

    /**
     *  参考 @see R  里面的同方法
     *  区别： 此方法是将自己本身转化
     * @param toClass
     * @throws Exception
     */
    public <T> T thisToEntityLine2Upper(Class<T> toClass) throws Exception {
        MapUtil.mapKeySetLine2Upper( this);
        return thisToEntity(toClass);
    }


    /**
     *  参考 @see R  里面的同方法
     *  区别： 此方法是将自己本身转化
     * @param toObj
     * @throws Exception
     */
    public void thisToEntity(Object toObj) throws Exception {

        Map2JavaBeanUtil.transMap2Bean(this,toObj);
    }

    public void thisToEntity(Object toObj,boolean b,boolean baseName) throws Exception {

        Map2JavaBeanUtil.transMap2Bean(this,toObj,b,baseName);
    }

    /**
     *  参考 @see R  里面的同方法
     *  区别： 此方法是将自己本身转化
     * @param toClass
     * @throws Exception
     */
    public <T> T thisToEntity(Class<T> toClass) throws Exception {

        return (T) Map2JavaBeanUtil.transMap2Bean(this,toClass);
    }


    /**
     * 剔除分页参数
     */
    public  void removePageParam(){
        removeByKeyName1("page","limit","rowIndex");
    }


    /**
     * 剔除部分key
     */
    public  void removeByKeyName1(String... str){
        Iterator<String> iterator = this.keySet().iterator();
        while (true) {
            if (!iterator.hasNext()) break;
            String next = iterator.next();
            int i = 0;
            for (String s : str) {
                if (next.equals(s)) {
                    i++;
                    break;
                }
            }
            if (i!=0){
                iterator.remove();
            }
        }
    }


    public  void removeByKeyName2(String... str){
        Iterator<String> iterator = this.keySet().iterator();
        while (true) {
            if (!iterator.hasNext()) break;
            String next = iterator.next();
            int i = 0;
            for (String s : str) {
                if (next.equals(s)) {
                    i++;
                    break;
                }
            }
            if (i==0){
                iterator.remove();
            }
        }
    }


    /**
     * 根据key循环移除空值
     * @param map
     */
    public void removeByKey(Map map){
        Iterator<Entry<String, Object>> iterator  = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, Object> entry = iterator.next();
            Object value = entry.getValue();
            if("".equals(value)||null==value){
                iterator.remove();
            }
        }
    }


    /**
     * 初始化分页参数 : 当需要分页的接口，但是需要给为空的分页参数一个默认值时可以调用此方法
     * page:0 & limit:10 是mybatis plus  Page 当中的默认值
     */
    public void initPageArg(){
        if (this.get("page") == null||this.get("page").equals("")) {
            this.put("page",0);
        }
        if (this.get("limit") == null||this.get("limit").equals("")) {
            this.put("limit",10);
        }
    }


    /**
     * 批量将一些key转化为int类型
     * @param names
     */
    public void batchToInt(String... names){
        for (String name : names) {
            if(this.get(name)!=null && this.get(name) instanceof Integer){
                continue;
            }
            String s = (String) this.get(name);
            if (s != null && !"".equals(s)) {
                this.put(name, Integer.parseInt(s));
            }
        }
    }


    public String getSqlDateByString(String key){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String string = getString(key);
            if (string==null){
                return null;
            }
//            java.sql.Date date = new java.sql.Date(.getTime());
            Calendar calendar= Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(string));
            calendar.set(Calendar.HOUR,0);//小时设置为0
            calendar.set(Calendar.MINUTE, 0);//分钟设置为0
            calendar.set(Calendar.SECOND, 0);//秒设置为0
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            calendar.add(Calendar.HOUR_OF_DAY, 16);
//            calendar.add(Calendar.se, -16);
            long timeInMillis = calendar.getTimeInMillis();
            Date date = new Date(timeInMillis);
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return simpleDateFormat1.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Date getDate(String key){
        return new Date(getLong(key));
    }


    public  Boolean getBoolean(String key){
            return getBoolean(key,"true");
    }

    public  Boolean getBoolean(String key,String trues){
        String string = MapUtil.getString(this, key);
        if(string!=null)
            return string.equals(trues);
        return null;
    }

    /**
     * 获取string 数组 不需要加上浏览器带的[]
     * @return
     */
    public String[] getStringArray(String name){
        if (name==null){
            return null;
        }
        if (name.indexOf("[]")==-1){
            name = name+"[]";
        }
        String[] parameterValues = getRequest().getParameterValues(name);
        if (parameterValues == null||parameterValues.length==0) {
            return null;
        }
        return parameterValues;
    }

    public String getString(String key) {
        return MapUtil.getString(this,key);
    }

    public Integer getInt(String key) {
        return MapUtil.getInt(this,key);
    }

    public Long getLong(String key) {
        return MapUtil.getLong(this,key);
    }

    public Double getDouble(String key) {
        return MapUtil.getDouble(this,key);
    }

    public Float getFloat(String key) {
        return MapUtil.getFloat(this,key);
    }

    public BigDecimal getBigDecimal(String key) {
//        String.v
        String value = String.valueOf(this.get(key));
        if (value.equals("null")) {
            return null;
        }
        value = value.trim();
        return new BigDecimal(value);
    }






}
