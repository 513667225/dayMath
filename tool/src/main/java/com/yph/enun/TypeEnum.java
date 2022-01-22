package com.yph.enun;

/**
 * @author Agu
 */
public enum TypeEnum {


    WORD(1,"wordCommission","文字"),IMG(2,"imgCommission","图片"),VIDEO(3,"videoCommission","视频");


    private  Integer typeId;
    private  String  typeColumn;
    private  String name;


     TypeEnum(Integer typeId, String typeColumn,String name) {
        this.typeId = typeId;
        this.typeColumn = typeColumn;
        this.name = name;
    }


    public  static  String getColumn(Integer typeId){
        for (TypeEnum value : values()) {
            if (value.getTypeId().intValue() == typeId.intValue()) {
                return value.getTypeColumn();
            }
        }
        return null;
    }

    public  static  Integer getId(String  name){
        for (TypeEnum value : values()) {
            if (value.getName().equals(name)) {
                return value.getTypeId();
            }
        }
        return null;
    }



    public Integer getTypeId() {

        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeColumn() {
        return typeColumn;
    }

    public void setTypeColumn(String typeColumn) {
        this.typeColumn = typeColumn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
