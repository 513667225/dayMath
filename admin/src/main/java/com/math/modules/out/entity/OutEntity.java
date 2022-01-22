package com.math.modules.out.entity;

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
 * @since 2021-11-10
 */
@TableName("`out`")
public class OutEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "out_id", type = IdType.AUTO)
    private Integer outId;

    /**
     * 时间
     */
    private Date times;

    /**
     * asin
     */
    private String asin;

    /**
     * PARENTASIN
     */
    private String parentasin;

    /**
     * msku
     */
    private String msku;

    /**
     * sku
     */
    private String sku;

    /**
     * 标题
     */
    private String title;

    /**
     * 品名
     */
    private String name;

    /**
     * 负责人
     */
    private String user;

    /**
     * 分类
     */
    private String type;

    /**
     * 标签
     */
    private String lable;

    /**
     * 店铺
     */
    private String shop;

    /**
     * 国家
     */
    private String country;

    /**
     * 可售
     */
    private Integer fba;

    /**
     * 销量
     */
    private Integer saleVal;


    /**
     *
     * 单量
     *
     */

    private  Integer orderCount;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getOutId() {
        return outId;
    }

    public OutEntity setOutId(Integer outId) {
        this.outId = outId;
        return this;
    }
    public Date getTimes() {
        return times;
    }

    public OutEntity setTimes(Date times) {
        this.times = times;
        return this;
    }
    public String getAsin() {
        return asin;
    }

    public OutEntity setAsin(String asin) {
        this.asin = asin;
        return this;
    }
    public String getParentasin() {
        return parentasin;
    }

    public OutEntity setParentasin(String parentasin) {
        this.parentasin = parentasin;
        return this;
    }
    public String getMsku() {
        return msku;
    }

    public OutEntity setMsku(String msku) {
        this.msku = msku;
        return this;
    }
    public String getSku() {
        return sku;
    }

    public OutEntity setSku(String sku) {
        this.sku = sku;
        return this;
    }
    public String getTitle() {
        return title;
    }

    public OutEntity setTitle(String title) {
        this.title = title;
        return this;
    }
    public String getName() {
        return name;
    }

    public OutEntity setName(String name) {
        this.name = name;
        return this;
    }
    public String getUser() {
        return user;
    }

    public OutEntity setUser(String user) {
        this.user = user;
        return this;
    }
    public String getType() {
        return type;
    }

    public OutEntity setType(String type) {
        this.type = type;
        return this;
    }
    public String getLable() {
        return lable;
    }

    public OutEntity setLable(String lable) {
        this.lable = lable;
        return this;
    }
    public String getShop() {
        return shop;
    }

    public OutEntity setShop(String shop) {
        this.shop = shop;
        return this;
    }
    public String getCountry() {
        return country;
    }

    public OutEntity setCountry(String country) {
        this.country = country;
        return this;
    }
    public Integer getFba() {
        return fba;
    }

    public OutEntity setFba(Integer fba) {
        this.fba = fba;
        return this;
    }
    public Integer getSaleVal() {
        return saleVal;
    }

    public OutEntity setSaleVal(Integer saleVal) {
        this.saleVal = saleVal;
        return this;
    }

    @Override
    public String toString() {
        return "OutEntity{" +
            "outId=" + outId +
            ", times=" + times +
            ", asin=" + asin +
            ", parentasin=" + parentasin +
            ", msku=" + msku +
            ", sku=" + sku +
            ", title=" + title +
            ", name=" + name +
            ", user=" + user +
            ", type=" + type +
            ", lable=" + lable +
            ", shop=" + shop +
            ", country=" + country +
            ", fba=" + fba +
            ", saleVal=" + saleVal +
        "}";
    }
}
