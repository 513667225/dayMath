package com.yph.util;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Map2JavaBeanUtil {
	/**
	 * 当把Person类作为BeanUtilTest的内部类时，程序出错<br>
	 * java.lang.NoSuchMethodException: Property '**' has no setter method<br>
	 * 本质：内部类 和 单独文件中的类的区别 <br>
	 * BeanUtils.populate方法的限制：<br>
	 * The class must be public, and provide a public constructor that accepts no arguments. <br>
	 * This allows tools and applications to dynamically create new instances of your bean, <br>
	 * without necessarily knowing what Java class name will be used ahead of time
	 */
	/**
	 * @Description: 大写转下划线，例如uName-->u_name
	 * @param key
	 * @return String
	 * @throws
	 *
	 */
	public static String transUpper2UnderLine(String key) {
		key = key.replaceAll("([A-Z])", "_$1");
		return key.toLowerCase();
	}
	
	
	/**
	 * @Description: 大写转下划线，例如uName-->u_name
	 * @param key
	 * @return String
	 * @throws
	 *
	 */
	public static String transUnderLine2Upper(String key) {
		Pattern p = Pattern.compile("_(.)");
		Matcher m = p.matcher(key);
		while(m.find()) {
			key = key.replaceAll(m.group(), m.group(1).toUpperCase());
		}
		return key;
	}
	
	/**
	 * @Description: 将obj强转为指定类型
	 * @param
	 * @param c
	 * @return Object
	 * @throws
	 *
	 */
	public static Object castObject2Type(Object cell, Class<?> c) {
		String xclass = c.getName();
		if("java.lang.String".equals(xclass)) {
			return String.valueOf(cell);
		} else if("java.util.Date".equals(xclass)) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sf.parse(String.valueOf(cell));
			} catch (ParseException e) {
				SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
				try {
					return sf1.parse(String.valueOf(cell));
				} catch (ParseException e1) {
				}
			}
		} else if("java.lang.Boolean".equals(xclass)) {
			Boolean boolname = true;
			if("否".equals(cell)) { 
				boolname=false; 
			}
			return boolname;
		} else if("java.math.BigDecimal".equals(xclass)) {
			return new BigDecimal(String.valueOf(cell));
		} else if("java.lang.Double".equals(xclass)||double.class.getName().equals(xclass)) {
			return new Double(String.valueOf(cell));
		}	else if("java.lang.Integer".equals(xclass)||int.class.getName().equals(xclass)) {
			return Integer.valueOf(String.valueOf(cell));
		}
		return cell;
	}

	public static void transMap2Bean(Map map, Object obj) throws Exception{
		transMap2Bean(map,obj,false,false);
	}

	public static Method getWriteMethod(Class clazz,String name) throws Exception{
		String fast = name.substring(0, 1);
		String end =  name.substring(1);

		Method method = clazz.getDeclaredMethod("set" + fast.toUpperCase()+end, clazz.getDeclaredField(name).getType());
		method.setAccessible(true);
		return method;
	}

    // Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
    @SuppressWarnings("unchecked")
	public static void transMap2Bean(Map map, Object obj,boolean isUpper2Line,boolean isBaseName) throws Exception{
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {

			String key= property.getDisplayName();
			if (isBaseName){
				Field baseName = PropertyDescriptor.class.getDeclaredField("baseName");
				baseName.setAccessible(true);
				key = String.valueOf(baseName.get(property));
			}
			if (isUpper2Line){
				key = transUpper2UnderLine(property.getName());
			}
            if (map.containsKey(key)) {
                Object value = map.get(key);
                if(value == null || "".equals(String.valueOf(value))) {
                	continue;
                }
                // 得到property对应的setter方法

                Method setter = getWriteMethod(obj.getClass(),property.getName());
				if(value.getClass().getName().indexOf("List") > 0) {
					Class<?> beanClass = getActualType(obj, property.getName(), 0);
					if(beanClass == null) {
						setter.invoke(obj, value);	
						continue;
					}
					List<Object> list = new ArrayList<Object>();
					for(Map<String, Object> bean : (List<Map<String, Object>>) value) {
						list.add(transMap2Bean(bean, beanClass));
					}
					setter.invoke(obj, list);
				} else {
					Object pvalue = castObject2Type(value, property.getPropertyType());
					setter.invoke(obj, pvalue);	
				}
            }
        }
    }
    
    /**
     * @Description: 获取泛型参数
     * @param obj 类
     * @param propertyName 属性名
     * @param num 第几个泛型参数,从0开始
     * @throws NoSuchFieldException
     * @throws SecurityException 
     * @return Class<?>
     *
     */
    public static Class<?> getActualType(Object obj, String propertyName, int num) throws Exception {
    	Class<?> clazz = obj.getClass();
    	Field f = clazz.getDeclaredField(propertyName);
    	Type gType = f.getGenericType();
    	if (gType instanceof ParameterizedType) {
    		ParameterizedType pType = (ParameterizedType) gType;
    		//Type rType = pType.getRawType();
    		//System.out.println("原始类型是："+rType);
    		Type[] tArgs = pType.getActualTypeArguments();
    		/*for (int i = 0; i < tArgs.length; i++) {
    			System.out.println("第"+i+"个泛型类型是："+tArgs[i]);
    		}*/
    		return Class.forName(tArgs[num].toString().substring(6));
    	}
    	return null;
    }
    
 // Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
    public static <T> T transMap2Bean(Map map, Class<T> obj) throws Exception {
    	 T t = obj.newInstance();
    	 transMap2Bean(map, t);
    	return t;
    }

    // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
    @SuppressWarnings("unchecked")
	public static Map<String, Object> transBean2Map(Object obj) throws Exception {

        if(obj == null){
            return null;
        }        
        Map<String, Object> map = new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            // 过滤class属性
            if (!key.equals("class")) {
                // 得到property对应的getter方法
                Method getter = property.getReadMethod();
                Object value = getter.invoke(obj);
				if(value == null || "".equals(String.valueOf(value))) {
                	continue;
                }
				if(value.getClass().getName().indexOf("List") > 0) {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					for(Object bean : (List<Object>) value) {
						list.add(transBean2Map(bean));
					}
					map.put(transUpper2UnderLine(key), list);
				} else {
	                map.put(transUpper2UnderLine(key), value);
				}
            }

        }
        return map;
    }
}
