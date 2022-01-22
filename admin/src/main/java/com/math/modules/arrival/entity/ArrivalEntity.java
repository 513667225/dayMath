package com.math.modules.arrival.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2022-01-10
 */
@TableName("arrival")
public class ArrivalEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "arrival_id", type = IdType.AUTO)
    private Integer arrivalId;

    private String asin;

    private String shipmentId;

    private Date times;

    private Integer count;

    private String country;

    public Integer getArrivalId() {
        return arrivalId;
    }

    public ArrivalEntity setArrivalId(Integer arrivalId) {
        this.arrivalId = arrivalId;
        return this;
    }
    public String getAsin() {
        return asin;
    }

    public ArrivalEntity setAsin(String asin) {
        this.asin = asin;
        return this;
    }
    public String getShipmentId() {
        return shipmentId;
    }

    public ArrivalEntity setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
        return this;
    }
    public Date getTimes() {
        return times;
    }

    public ArrivalEntity setTimes(Date times) {
        this.times = times;
        return this;
    }
    public Integer getCount() {
        return count;
    }

    public ArrivalEntity setCount(Integer count) {
        this.count = count;
        return this;
    }
    public String getCountry() {
        return country;
    }

    public ArrivalEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    @Override
    public String toString() {
        return "ArrivalEntity{" +
            "arrivalId=" + arrivalId +
            ", asin=" + asin +
            ", shipmentId=" + shipmentId +
            ", times=" + times +
            ", count=" + count +
            ", country=" + country +
        "}";
    }
}
