package com.yph.paramter;

/**
 * @author Agu
 */
public class MqParamter {



    public static final String ORDER_DELAY_EXCHANGE = "user.order.delay.exchange";

    public static final String ORDER_DELAY_QUEUE = "user.order.delay.queue";

    public static final String ORDER_DELAY_ROUTING_KEY = "order_delay";

    public static final String ORDER_QUEUE_NAME = "user.order.queue";

    public static final String ORDER_EXCHANGE_NAME = "user.order.exchange";

    public static final String ORDER_ROUTING_KEY = "order";


    public   static  final  String USER_QUEUE_NAME = "userQueue";


    public   static  final  String USER_BEAN_QUEUE_NAME = "userBeanQueue";

    public   static  final  String TX_QUEUE_NAME = "txQueue";

    public  static  final  String ALLOCATION_QUEUE_NAME = "allocationQueue";

    public static final String TEAM_ENERGY_SUM_QUEUE ="teamEnergySumQueue";

    public static final String LEVEL_UP_QUEUE  ="levelUpQueue";

    public static  final  String CREATE_USER_ADDRESS = "createUserQueue";

    public  static  final  String TRADING = "tradingQueue";

    public static final String USER_BILL_QUEUE="userBillQueue";

    //拼团队列
    public  static  final  String SpellQueue = "spellQueue";
    //未拼中队列队列
    public  static  final  String LouseQueue = "loserQueues";
    //组团队列
    public  static  final  String CreateGroupQueue = "createGroupQueue";
    //组团队列--》档次
    public  static  final  String CreateGroupQueue2 = "createGroupQueue2";
    //组团队列--》档次
    public  static  final  String CreateGroupQueue3 = "createGroupQueue3";
    //组团队列--》档次
    public  static  final  String CreateGroupQueue4 = "createGroupQueue4";
    //省市代分成队列
    public  static  final  String ShareOutBonusQueue = "shareOutBonusQueue";
    //中奖
    public  static final String BingoQueue = "bingoQueue";
    //拼团记录
    public  static  final  String SpellBill  = "spellBillQueue";

    public  static  final  String WITHDRAW = "withdrawQueue";

    public  static  final  String COMMENT = "commentQueue";

    //无限拼
    public static final  String INFINITI  = "infinitiQueue";
}
