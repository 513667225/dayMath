package com.math.modules.fbaProduct.entity;

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
 * @since 2021-12-23
 */
@TableName("fba_product")
public class FbaProductEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "fba_product_id",type = IdType.AUTO)
    private Integer fbaProductId;

    private String shipmentId;

    private String seller;

    private String shipmentStatus;

    private String shipmentName;

    private Date gmtCreate;

    private String destinationFulfillmentCenterId;

    private Date gmtModified;

    private  Date syncTime;

    private Integer sellerId;


    private Integer isClosed;

    public Integer getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(Integer isClosed) {
        this.isClosed = isClosed;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(Date syncTime) {
        this.syncTime = syncTime;
    }

    public Integer getFbaProductId() {
        return fbaProductId;
    }

    public FbaProductEntity setFbaProductId(Integer fbaProductId) {
        this.fbaProductId = fbaProductId;
        return this;
    }
    public String getShipmentId() {
        return shipmentId;
    }

    public FbaProductEntity setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
        return this;
    }
    public String getSeller() {
        return seller;
    }

    public FbaProductEntity setSeller(String seller) {
        this.seller = seller;
        return this;
    }
    public String getShipmentStatus() {
        return shipmentStatus;
    }

    public FbaProductEntity setShipmentStatus(String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
        return this;
    }
    public String getShipmentName() {
        return shipmentName;
    }

    public FbaProductEntity setShipmentName(String shipmentName) {
        this.shipmentName = shipmentName;
        return this;
    }
    public Date getGmtCreate() {
        return gmtCreate;
    }

    public FbaProductEntity setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }
    public String getDestinationFulfillmentCenterId() {
        return destinationFulfillmentCenterId;
    }

    public FbaProductEntity setDestinationFulfillmentCenterId(String destinationFulfillmentCenterId) {
        this.destinationFulfillmentCenterId = destinationFulfillmentCenterId;
        return this;
    }

    @Override
    public String toString() {
        return "FbaProductEntity{" +
            "fbaProductId=" + fbaProductId +
            ", shipmentId=" + shipmentId +
            ", seller=" + seller +
            ", shipmentStatus=" + shipmentStatus +
            ", shipmentName=" + shipmentName +
            ", gmtCreate=" + gmtCreate +
            ", destinationFulfillmentCenterId=" + destinationFulfillmentCenterId +
        "}";
    }
}
