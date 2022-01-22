package com.yph.param;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Agu
 */
public class SystemParam {


    public static final String LOGIN_COOKIE_KEY = "math_loginUser";

    public static final String SYSTEM_USER_NAME = "System";


    //任务过期时间
    public static final Integer TASK_EVP_TIME = 5;
    //任务过期时间的单位
    public static final TimeUnit TASK_EVP_TIME_UNIT = TimeUnit.MINUTES;


    public  static  final List<String> countrys= Arrays.asList("美国","德国","英国","澳大利亚"
            ,"日本","阿联酋");


    //任务过期时间
    public static final Integer SHOP_EXP_TIME = 5;
    //任务过期时间的单位
    public static final TimeUnit SHOP_EXP_TIME_UNIT = TimeUnit.MINUTES;
}
