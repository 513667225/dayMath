package com.yph.constant;

public class UserConstant {

    /**
     * 红包ID前缀
     */
    public static final String RED_PACKET_PAST_DUE_PREFIX = "red_packet_past_due_prefix:";

    /**
     * 红包具体ID集合前缀
     */
    public static final String RED_PACKET_LIST_PREFIX = "red_packet_list_prefix:";

    /**
     * 已抢红包用户集合前缀
     */
    public static final String HAS_BEEN_ROBBED_RED_PACKET = "has_been_robbed_red_packet:";

    /**
     * 用户每日抢红包的总钱前缀
     */
    public static final String USER_ROB_RED_PACKER = "user_rob_red_packer:";

    /**
     * 用户每日抢红包的总钱最大额前缀
     */
    public static final String USER_ROB_RED_PACKER_MAX = "user_rob_red_packer_max";

    /**
     * 用户45天抢红包的总钱前缀
     */
    public static final String USER_ROB_RED_PACKER45 = "user_rob_red_packer45:";

    /**
     * 用户45天抢红包的总钱最大额前缀
     */
    public static final String USER_ROB_RED_PACKER_MAX45 = "user_rob_red_packer_max45";

    /**
     * 系统发红包的数量今日总共能量源是多少
     */
    public static final String SUM_RED_PACKER_MONEY = "sum_red_packer_money:";

    //处理过期视频队列
    public static final String REDIS_PACKER_QUEUE_PREFIX = "REDIS_PACKER_QUEUE_PREFIX";
    public static final String SYSTEM_IS_LOGIN = "system_is_login";

    /**
     * 用户注册队列
     */
    public static String USER_REGISTER_QUEUE="user_register_queue";


    /**
     * 发送短信验证码的前缀
     */
    public static String SYSTEM_SMS="system_sms";


    /**
     * 系统中一共有多少能量源
     */
    public static String SYSTEM_SUM_ENERGYSOURCE="system_sum_energysource";

    /**
     * 系统中一共有多少生命源
     */
    public static String SYSTEM_SUM_LIFESOURCE="system_sum_lifesource";

    /**
     * 当前正在同步能量源标识
     */
    public static  String SYSTEM_SYNC_ENERGY_SOURCE="system_sync_energy_source";
}
