package com.math.modules.size.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2021-11-10
 */
@TableName("size")
public class SizeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "size_id", type = IdType.AUTO)
    private Integer sizeId;

    private String asin;

    private String site;

    private String shipmentId;

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

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

    private Integer sizeCount;

    public Integer getSizeId() {
        return sizeId;
    }

    public SizeEntity setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
        return this;
    }
    public Integer getSizeCount() {
        return sizeCount;
    }

    public SizeEntity setSizeCount(Integer sizeCount) {
        this.sizeCount = sizeCount;
        return this;
    }

    @Override
    public String toString() {
        return "SizeEntity{" +
            "sizeId=" + sizeId +
            ", sizeCount=" + sizeCount +
        "}";
    }
}
