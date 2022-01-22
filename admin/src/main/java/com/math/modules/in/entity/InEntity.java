package com.math.modules.in.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-11-10
 */
@TableName("`in`")
public class InEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "in_id", type = IdType.AUTO)
    private Integer inId;

    private String asin;

    private String site;

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    private Integer count;

    @TableField("shipment_id")
    private String shipmentId;

    private Date times;

    private String  code;

    private  Integer addSign;

    public Integer getAddSign() {
        return addSign;
    }

    public void setAddSign(Integer addSign) {
        this.addSign = addSign;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
    }

    public Integer getInId() {
        return inId;
    }

    public InEntity setInId(Integer inId) {
        this.inId = inId;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public InEntity setCount(Integer count) {
        this.count = count;
        return this;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public InEntity setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
        return this;
    }

    @Override
    public String toString() {
        return "InEntity{" +
                "inId=" + inId +
                ", count=" + count +
                ", shipmentId=" + shipmentId +
                "}";
    }
}
