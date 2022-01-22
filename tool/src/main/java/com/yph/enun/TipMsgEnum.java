package com.yph.enun;

/**
 * @author Agu
 */
public enum TipMsgEnum {

    DEFAULT_ERROR_MSG(500,"error"),
    DangerMsg(501,"请刷新后重试"), // 参数错误，非法请求
    TOKEN_OF_OVERDUE(1001,"Token过期"),
    TOKEN_OF_Excption(1002,"Token异常"),
    TOKEN_CREATE_Excption(1003,"JWT签名失败"),
    TOKEN_NULL_Excption(1004,"无Token参数"),
    PARAMETER_NULL_Excption(1005,"缺少必须参数"),
    UPDATE_MONRY_FAIL(1006,"修改金钱相关失败"),
    INSERT_USER_ADDRESS(1007,"用户地址添加失败"),
    UPDATE_USER_ADDRESS(1008,"用户地址修改失败"),
    DELETE_USER_ADDRESS(1009,"用户地址删除失败"),
    MONEY_MAX(1010,"成本金额大于5折"),
    SHOP_USER_NOT_LOGIN(1011,"商家用户未登录"),
    ADMIN_USER_NOT_LOGIN(1012,"系统用户未登录"),
    USER_EX(1011,"用户登录出错"),
    DECIPHERING_PHONE(1011,"解密手机号失败"),
    SESSIONKEY_STRLE(1011,"sessionkey过期"),
    USER_EX1(1011,"用户登录出错"),
    FORSALE_EXCPTION(1012,"代售异常"),
    USER_SIZR_EXCPTION(1013,"代售次数不够"),
    NOT_SUFFICIENT_FUNDS(1014,"余额不足"),
    MONEY_TYPE_EXCPTION(1015,"用户金额相关类型错误"),
    SPELL_GROUP_INVENTORY_INSUFFICIENT(1016,"拼团卷库存不足"),
    NOT_SHOPPING_GOLD_FUNDS(1021,"可用能量源不足"),
    ADVERTISING_VIDEO_EXCPTION(1017,"添加广告视频失败"),
    USER_SPELL_ING_ERROR(1019, "用户有正在参与的拼团,不可重复参与"),  //{code:1019
    // msg : 用户有正在参与的拼团,不可重复参与
    // }
    USER_CONVERSION_INTEGRAL_EXCPTION(1018,"添加用户兑换积分权益失败"),
    VIDEO_EMPTY_EXCPTION(1020,"暂无推荐视频"),
    AVAILABLE_STOCK_INSUFFICIENT(1022,"当前商品可用库存不足"),
    REFERRER_ID_INVALID(1023,"推荐人ID无效"),
    LACK_OF_MONEY(1024,"金额不足"),
    TEMP_CODE(1025,"暂不支持手机号登陆"),
    PHONE_REPETITION(1026,"此手机号已被其他用户绑定"),
    RED_PACKET_GRAB_THE(7000,"你的手慢了"),
    RED_PACKET_STALE(7001,"超过24未被领取,已过期"),
    NOT_SUFFICIENT_ENERGY_YUAN(7008,"您的能量元不足");

    private  int code;

    private String msg;

    public  static TipMsgEnum forMsg(String msg){
        for (TipMsgEnum value : values()) {
            if (value.getMsg().equals(msg)) {
                return value;
            }
        }
        return DEFAULT_ERROR_MSG;
    }


      TipMsgEnum(int code, String msg){
          this.code = code;
          this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
