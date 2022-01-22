package com.yph.paramter;

/**
 * @author Agu
 */
public class GroupQueueParameter {

    /**
     * 当前排队人数的redis-key
     */
    public  static  final  String GROUP_QUEUE_NUMBER = "groupQueueNumber";
    /**
     * 当前排队的人(所有档次)的redis-key
     */
    public  static  final String QUEUE_MAP = "QUEUE_MAP";
    /**
     * 暂无使用
     */
    public  static  final String QUEUE_MAP_SET = "QUEUE_MAP_SET";
    /**正在拼团的人的redis-key*/
    public  static  final String SPELL_MAP = "SPELL_MAP";
    /**排队队列的redis-key*/
    public  static final String SPELL_QUEUE = "SPELL_QUEUE "; //根据档次
   /** 排队队列的redis-key副本*/
    public  static final String SPELL_QUEUE_COPY = "SPELL_QUEUE_COPY";
    /**取消拼团redis-key*/
    public  static  final  String  CANCEL_QUEUE = "CANCEL_QUEUE";

    /**
     *   redis正在组团的标识
     */
    public  static  final  String CANCELING_QUEUE = "CANCELING_QUEUE_BIT:";

    /**
     *MQ正在组团的标识
     */
    public  static  final  String MQ_CREATE_QUEUE = "MQ_CREATE_QUEUE:";

    /*当前拼团商品库存预减库存值 redis_key   需要保证PRODUCT_STOCK<=SpellUtil.getStockMaxExpectedValue();*/
    public  static  final  String PRODUCT_PRE_STOCK = "PRODUCT_PRE_STOCK:";
    //当前拼团卷库存预减库存值 redis_key 同上
    public  static  final  String ROLL_PRE_STOCK = "ROLL_PRE_STOCK:";

    //当前拼团商品库存选中值 redis_key 当需要扣减PRODUCT_STOCK时  需SELECTED_PRODUCT_STOCK<=SpellUtil.getStockMaxExpectedValue()
    public  static  final  String SELECTED_PRODUCT_STOCK = "SELECTED_PRODUCT_STOCK:";
    //当前拼团卷库存选中值 redis_key 同上
    public  static  final  String SELECTED_ROLL_STOCK = "SELECTED_ROLL_STOCK:";

}
