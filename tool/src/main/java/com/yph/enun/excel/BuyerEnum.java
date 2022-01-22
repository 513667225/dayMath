package com.yph.enun.excel;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Agu
 */
public enum  BuyerEnum {
    WX("微信","we_chat"),
    EMAIL("邮箱","contact_details"),
    EMAIL2("邮箱2","contact_details2"),
    NAME("姓名","name"),
    PAYPAL("Paypal","paypal"),
    FB("FB","facebook")
    ;

    private  String name;

    private  String column;


    BuyerEnum(String name, String column) {
        this.name = name;
        this.column = column;
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


    public  static  String getColumnByName(String  name){
        for (BuyerEnum value : values()) {
            if (value.getName().equals(name)) {
                return value.getColumn();
            }
        }
        return NAME.getColumn();
    }

    public static Map<String,Object> getAll(){
        Map<String,Object> hashMap = new HashMap<>();
        for (BuyerEnum value : values()) {
            hashMap.put(value.getName(),value.getColumn());
        }
        return hashMap;
    }

}
