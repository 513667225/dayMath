package com.yph.paramter;

/**
 * 返现配置参数类
 * @author Agu
 */
public class ReMoney {

    //直推津贴系数
    public  static  double  directPush  = 0.05;
    //间推津贴系数
    public  static  double  indirectPush  = 0.05;
    //拼团失败余额返利系数
    public  static double   balanceRate = 1.0;
    //拼团失败积分豆豆返利系数
    public  static double  integralRate = 0;

    //推荐and伞下分成余额返利系数
    public static double  allowanceToBalanceRate = 0.8;
    //推荐and伞下分成豆子返利系数
    public static double  allowanceToBeanRate = 0.2;
    //缓存用户级别关系key前缀
    public  static  String USER_LEVEL_CACHE_KEY = "divide";
    //缓存中奖人数的key
    public  static  String BINGO_KEY = "bingoKey";
    //中奖队列副本
    public  static  String BINGO_KEY_COPY = "bingoKeyCopy";


}
