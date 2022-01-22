package com.math.modules.seller.entity;

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
@TableName("seller")
public class SellerEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    private String sellerId;
    @TableId(value = "sel_id", type = IdType.AUTO)
    private Integer selId;

    private Integer mid;

    private String name;

    private Integer sid;

    private String accountName;

    private String sellerAccountId;

    private String region;

    private String country;

    public Integer getSid() {
        return sid;
    }

    public SellerEntity setSid(Integer sid) {
        this.sid = sid;
        return this;
    }
    public Integer getMid() {
        return mid;
    }

    public SellerEntity setMid(Integer mid) {
        this.mid = mid;
        return this;
    }
    public String getName() {
        return name;
    }

    public SellerEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getSelId() {
        return selId;
    }

    public void setSelId(Integer selId) {
        this.selId = selId;
    }

    public String getAccountName() {
        return accountName;
    }

    public SellerEntity setAccountName(String accountName) {
        this.accountName = accountName;
        return this;
    }
    public String getSellerAccountId() {
        return sellerAccountId;
    }

    public SellerEntity setSellerAccountId(String sellerAccountId) {
        this.sellerAccountId = sellerAccountId;
        return this;
    }
    public String getRegion() {
        return region;
    }

    public SellerEntity setRegion(String region) {
        this.region = region;
        return this;
    }
    public String getCountry() {
        return country;
    }

    public SellerEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    @Override
    public String toString() {
        return "SellerEntity{" +
            "sid=" + sid +
            ", mid=" + mid +
            ", name=" + name +
            ", sellerId=" + sellerId +
            ", accountName=" + accountName +
            ", sellerAccountId=" + sellerAccountId +
            ", region=" + region +
            ", country=" + country +
        "}";
    }
}
