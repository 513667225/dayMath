package com.yph.enun.excel;

/**
 * @author Agu
 */
public enum GjEnum {
    US("US","美国",1),
    UK("UK","英国",2),
    DE("DE","德国",3),
    FR("FR","法国",9),
    ES("ES","西班牙",5),
    IT("IT","意大利",4),
    CA("CA","加拿大",8),
    JP("JP","日本",7)
    ;


    private  String code;

    private  String name;

    private  Integer id;

    GjEnum(String code, String name, Integer id) {
        this.code = code;
        this.name = name;
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public  static  GjEnum getValueByCode(String code){

        for (GjEnum value : values()) {
            if (value.getCode().equals(code)){
                return value;
            }
        }
        return GjEnum.US;
    }

}
