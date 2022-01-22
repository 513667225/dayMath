package com.yph.enun;

/**
 * @author Agu
 */
public enum RateEnum {
    DOLLAR("美国","dollarRate","美元"),EURO("德国","euroRate","欧元"),
    YDL("意大利","euroRate","欧元"),
    XBY("西班牙","euroRate","欧元"),
    FG("法国","euroRate","欧元"),
    JND("加拿大","canadaRate","加拿大元"),
    POUND("英国","poundRate","英镑"),
    MXG("墨西哥","mexicanPeso","墨西哥比索"),
    RB("日本","japaneseYenRate","日元");


    private String name;

    private  String column;

    private  String moneyName;

    RateEnum(String name, String column, String moneyName) {
        this.name = name;
        this.column = column;
        this.moneyName = moneyName;
    }

    public  static  RateEnum getColumnByName(String name){
        for (RateEnum value : values()) {
            if (value.getName().equals(name)) {
                return value;
            }
        }
        return RateEnum.DOLLAR;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getMoneyName() {
        return moneyName;
    }

    public void setMoneyName(String moneyName) {
        this.moneyName = moneyName;
    }
}
