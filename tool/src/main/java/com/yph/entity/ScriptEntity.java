package com.yph.entity;

import java.math.BigDecimal;

/**
 * @author Agu
 */
public class ScriptEntity {

    private String type;

    private String name;

    private BigDecimal amount;

    private String address;


    public ScriptEntity() {

    }

    public ScriptEntity(String type, String name, BigDecimal amount, String address) {
        this.type = type;
        this.name = name;
        this.amount = amount;
        this.address = address;
    }




    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static ScriptEntity getScriptEntity(String script){
        String[] s = script.split(" ");
        ScriptEntity scriptEntity = new ScriptEntity();
        try {
            scriptEntity.setName(s[1].replaceAll("\"",""));
            scriptEntity.setType(s[0].replaceAll("\"",""));
            scriptEntity.setAmount(new BigDecimal(s[2].replaceAll("\"","")));
            scriptEntity.setAddress(s[3].replaceAll("\"",""));
        }catch (Exception e){
            scriptEntity = null;
        }
        return scriptEntity;
    }

}
