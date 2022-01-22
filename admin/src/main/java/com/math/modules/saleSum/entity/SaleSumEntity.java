package com.math.modules.saleSum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2021-12-23
 */
@TableName("sale_sum")
public class SaleSumEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sum_id", type = IdType.AUTO)
    private Integer sumId;

    private Date rDate;

    private String sellerSku;

    private String asin;

    private String productName;

    private String currencyCode;

    private Integer orderItems;

    private Integer volume;

    private BigDecimal amount;


    private  Integer sellerId;

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getSumId() {
        return sumId;
    }

    public SaleSumEntity setSumId(Integer sumId) {
        this.sumId = sumId;
        return this;
    }
    public Date getRDate() {
        return rDate;
    }

    public SaleSumEntity setRDate(Date rDate) {
        this.rDate = rDate;
        return this;
    }
    public String getSellerSku() {
        return sellerSku;
    }

    public SaleSumEntity setSellerSku(String sellerSku) {
        this.sellerSku = sellerSku;
        return this;
    }
    public String getAsin() {
        return asin;
    }

    public SaleSumEntity setAsin(String asin) {
        this.asin = asin;
        return this;
    }
    public String getProductName() {
        return productName;
    }

    public SaleSumEntity setProductName(String productName) {
        this.productName = productName;
        return this;
    }
    public String getCurrencyCode() {
        return currencyCode;
    }

    public SaleSumEntity setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }
    public Integer getOrderItems() {
        return orderItems;
    }

    public SaleSumEntity setOrderItems(Integer orderItems) {
        this.orderItems = orderItems;
        return this;
    }
    public Integer getVolume() {
        return volume;
    }

    public SaleSumEntity setVolume(Integer volume) {
        this.volume = volume;
        return this;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public SaleSumEntity setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public String toString() {
        return "SaleSumEntity{" +
            "sumId=" + sumId +
            ", rDate=" + rDate +
            ", sellerSku=" + sellerSku +
            ", asin=" + asin +
            ", productName=" + productName +
            ", currencyCode=" + currencyCode +
            ", orderItems=" + orderItems +
            ", volume=" + volume +
            ", amount=" + amount +
        "}";
    }
}
