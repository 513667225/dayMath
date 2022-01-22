package com.yph.param;

/**
 * @author Agu
 */
public class RedisParamenter {

    public  static  final  String ADMIN_LOING_USER_REDIS_KEY = "math_adminKey";

    //初始化商户默认汇率表key
    public  static  final  String INIT_SHOP_RATE_KEY = "initRateKey";
    public  static  final  String ASIN_KING_TOKEN = "asinKing";


    //商家汇率表前缀
    public static  final String SHOP_RATE_KEY="rate:shop_";

    //用户登陆rediskey
    public  static  final  String USER_REDIS_LOGIN_KEY = "math_user:login";

    //买手任务过期key
    public  static final String BUYER_TASK_EXP_KEY =  "taskExp:";

    //七天内只能接一单key
    public  static  final  String SHOP_KEY_EXP = "shopExp_:";

    //excel与数据库绑定key
    public  static  final  String EXCEL_DATABASE_TASK_BIND= "EXCEL_DATABASE_TASK_BIND";

}
