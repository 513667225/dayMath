package com.math.modules.fbaStore.entity;

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
 * @since 2021-12-23
 */
@TableName("fba_store")
public class FbaStoreEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "fbain_id", type = IdType.AUTO)
    private Integer fbainId;

    private String wname;

    private String productImage;

    private String asin;

    private String msku;

    private String fnsku;

    private String productName;

    private String sku;

    private Integer afnFulfillableQuantity;

    private Integer reservedFcTransfers;

    private Integer reservedFcProcessing;

    private  Integer sellerId;

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getFbainId() {
        return fbainId;
    }

    public FbaStoreEntity setFbainId(Integer fbainId) {
        this.fbainId = fbainId;
        return this;
    }
    public String getWname() {
        return wname;
    }

    public FbaStoreEntity setWname(String wname) {
        this.wname = wname;
        return this;
    }
    public String getProductImage() {
        return productImage;
    }

    public FbaStoreEntity setProductImage(String productImage) {
        this.productImage = productImage;
        return this;
    }
    public String getAsin() {
        return asin;
    }

    public FbaStoreEntity setAsin(String asin) {
        this.asin = asin;
        return this;
    }
    public String getMsku() {
        return msku;
    }

    public FbaStoreEntity setMsku(String msku) {
        this.msku = msku;
        return this;
    }
    public String getFnsku() {
        return fnsku;
    }

    public FbaStoreEntity setFnsku(String fnsku) {
        this.fnsku = fnsku;
        return this;
    }
    public String getProductName() {
        return productName;
    }

    public FbaStoreEntity setProductName(String productName) {
        this.productName = productName;
        return this;
    }
    public String getSku() {
        return sku;
    }

    public FbaStoreEntity setSku(String sku) {
        this.sku = sku;
        return this;
    }
    public Integer getAfnFulfillableQuantity() {
        return afnFulfillableQuantity;
    }

    public FbaStoreEntity setAfnFulfillableQuantity(Integer afnFulfillableQuantity) {
        this.afnFulfillableQuantity = afnFulfillableQuantity;
        return this;
    }
    public Integer getReservedFcTransfers() {
        return reservedFcTransfers;
    }

    public FbaStoreEntity setReservedFcTransfers(Integer reservedFcTransfers) {
        this.reservedFcTransfers = reservedFcTransfers;
        return this;
    }
    public Integer getReservedFcProcessing() {
        return reservedFcProcessing;
    }

    public FbaStoreEntity setReservedFcProcessing(Integer reservedFcProcessing) {
        this.reservedFcProcessing = reservedFcProcessing;
        return this;
    }

    @Override
    public String toString() {
        return "FbaStoreEntity{" +
            "fbainId=" + fbainId +
            ", wname=" + wname +
            ", productImage=" + productImage +
            ", asin=" + asin +
            ", msku=" + msku +
            ", fnsku=" + fnsku +
            ", productName=" + productName +
            ", sku=" + sku +
            ", afnFulfillableQuantity=" + afnFulfillableQuantity +
            ", reservedFcTransfers=" + reservedFcTransfers +
            ", reservedFcProcessing=" + reservedFcProcessing +
        "}";
    }
}
