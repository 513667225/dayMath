package com.yph.enun;


import com.yph.paramter.MqParamter;

/**
 * Mq配置相关参数
 *
 * @author Agu
 */
public enum MqParameterEnum {

    //拼团队列
    SpellQueue("spellQueue", "direct.key", "SpellDirectExchange"),
    //排队队列
    WaitQueue("waitQueue", "direct.key", "WaitDirectExchange"),
    //未拼中队列队列
    LouseQueue("loserQueues", "direct.key", "LoserDirectExchange"),
    //组团队列
    CreateGroupQueue("createGroupQueue", "direct.key", "createGroupExchange"),
    CreateGroupQueue2("createGroupQueue2", "direct.key", "createGroupExchange2"),
    CreateGroupQueue3("createGroupQueue3", "direct.key", "createGroupExchange3"),
    CreateGroupQueue4("createGroupQueue4", "direct.key", "createGroupExchange4"),
    //省市代分成队列
    ShareOutBonusQueue("shareOutBonusQueue", "direct.key", "shareOutBonusExchange"),
    //用户队列
    UserQueue(MqParamter.USER_QUEUE_NAME, "direct.key", "userExchange"),
    BingoQueue("bingoQueue","direct.key","bingoExchange"),
    AllocationQueue(MqParamter.ALLOCATION_QUEUE_NAME,"direct.key", "allocationExchange"),
    TeamEnergySumQueue(MqParamter.TEAM_ENERGY_SUM_QUEUE,"direct.key", "teamEnergySumExchange"),
    LevelUp(MqParamter.LEVEL_UP_QUEUE,"direct.key", "levelExchange"),
    TxQueue(MqParamter.TX_QUEUE_NAME,"direct.key","txExchange"),
    //创建用户地址
    CreateUserAddress(MqParamter.CREATE_USER_ADDRESS,"direct.key","createUserAddressExchange"),
    //发起交易
    Trading(MqParamter.TRADING,"direct.key","tradingExchange"),
    userBeanQueue(MqParamter.USER_BEAN_QUEUE_NAME,"direct.key","userBeanExchange"),
    UserBillQueue(MqParamter.USER_BILL_QUEUE,"direct.key","userBillExchange"),
    //提现状态查询
    WithdrawQueue(MqParamter.WITHDRAW,"direct.key","withdrawExchange"),
    commentQueue(MqParamter.COMMENT,"direct.key","commentExchange"),
    SpellBill(MqParamter.SpellBill,"direct.key","SpellBillExchange"),
    //无限拼队列
    INFINITI(MqParamter.INFINITI,"direct.key","infinitiExchange");

    String queueName;

    String exchangeKeyName;

    String exchangeName;

    MqParameterEnum(String queueName, String exchangeKeyName, String exchangeName) {
        this.exchangeKeyName = exchangeKeyName;
        this.exchangeName = exchangeName;
        this.queueName = queueName;
    }

    public String getQueueName() {
        return queueName;
    }

    public String getExchangeKeyName() {
        return exchangeKeyName;
    }

    public String getExchangeName() {
        return exchangeName;
    }
}
