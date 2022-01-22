package com.yph.util;

import com.yph.param.SystemParam;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    public static String getDateString(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSS+0800");
        Date now=new Date();
        String date = simpleDateFormat.format(now);
        return date;
    }


    public static String getNowDateString(){
        return getNowDateString(0);
    }
    public static String getNowDateString(int i){

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date now=new Date();
        if (i!=0)
        now = dayMath(now,i);
        String date = simpleDateFormat.format(now);
        return date;
    }


    public  static Date dayMath(Date date, int day){
        return dayMath( date,  day,null);
    }

    public  static Date dayMath(Date date, int day,Date lastTime){
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY,0);//小时设置为0
        calendar.set(Calendar.MINUTE, 0);//分钟设置为0
        calendar.set(Calendar.SECOND, 0);//秒设置为0
        calendar.add(Calendar.DAY_OF_MONTH, -day);
        Date time = calendar.getTime();
        if (lastTime!=null&&time.getTime()>lastTime.getTime()){
            time = lastTime;
        }
        return time;
    }





    public  static Date maxTime(){
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY,0);//小时设置为0
        calendar.set(Calendar.MINUTE, 0);//分钟设置为0
        calendar.set(Calendar.SECOND, 0);//秒设置为0
        calendar.add(Calendar.DAY_OF_YEAR, -3);
        return calendar.getTime();
    }

    public static  Integer daySubDay(Date day1,Date day2){
        long l = day1.getTime() - day2.getTime();
        int l1 = (int) (l / 1000 / 60 / 60 / 24);
        return l1;
    }


    public  static  Date stringToDate(String str){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getThisWeekMonday() {
        return getThisWeekMonday(getNowDateDay());
    }


    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

        public static void main(String[] args) throws InterruptedException {
//        System.out.println(getDateString());
//            RestTemplate restTemplate = new RestTemplate();
//            List<HttpMessageConverter<?>> httpMessageConverters = restTemplate.getMessageConverters();
//            httpMessageConverters.stream().forEach(httpMessageConverter -> {
//                if(httpMessageConverter instanceof StringHttpMessageConverter){
//                    StringHttpMessageConverter messageConverter = (StringHttpMessageConverter) httpMessageConverter;
//                    messageConverter.setDefaultCharset(Charset.forName("UTF-8"));
//                }
//            });
//            restTemplate.setMessageConverters(httpMessageConverters);
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            String[] str = {"B07KT2VF3H,禄英电子,78.88,1.25,2.6,Invicta,SKYSONIC,2","B07KT2VF3H,禄英电子,78.88,1.25,2.6,Invicta,SKYSONIC,2,后续的sku",};
//            Map<String,Object> map =new HashMap<>();
//            map.put("data",str);
//            map.put("cookie","auth_id=ut32ufhle5f1g5k1rgndpg54s3.2e1f9dd0076dd893ee1b; dc_vid=281193353; _ga=GA1.2.178199354.1638848107; _gid=GA1.2.520989450.1638848107; dc_uid=2028607; dc_medium=old_user; dc_source=old_user; dfrom=oldUser; ic_sf=297578-2; lang=zh-CN; currentPlatform=14; nc_id=2108979695562260484; langInSetting=zh-CN; dc_selid=90888583; Hm_lvt_573ef04e2ea5dc6dc333b887f5172b73=1638859684,1638860271,1639053758,1639077000; _gat_UA-42306011-4=1; Hm_lpvt_573ef04e2ea5dc6dc333b887f5172b73=1639102231");
//            String data = JSONUtils.toString(map);
//            HttpEntity<Object> objectHttpEntity = new HttpEntity<>(data,headers);
//            String forObject = restTemplate.postForObject("http://159.75.72.103:8340/?uploadtask", objectHttpEntity, String.class);
//            System.out.println(forObject);


        }






    public  static  Date getNowDateDay(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date now=new Date();
        String date = simpleDateFormat.format(now);

        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }





}
