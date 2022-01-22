package com.yph.entity;

import java.math.BigDecimal;

/**
 * @author Agu
 */
public class RateEntity {


    //汇率标识
    private  String id;
    //汇率注释
    private  String name;
    //汇率初始值
    private BigDecimal rate;


    public RateEntity() {
    }

    public RateEntity(String id, String name, BigDecimal rate) {
        this.id = id;
        this.name = name;
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
