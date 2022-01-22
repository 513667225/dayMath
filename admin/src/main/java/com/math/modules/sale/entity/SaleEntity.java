package com.math.modules.sale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-11-12
 */
@TableName("sale")
public class SaleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sale_id", type = IdType.AUTO)
    private Integer saleId;

    private String asin;

    /**
     * 品名
     */
    private String productName;

    /**
     * 站点
     */
    private String site;

    /**
     * 负责人
     */
    private String user;

    /**
     * 日期
     */
    private Date times;


    private  BigDecimal amount;




    /**
     * 销量
     */
    private BigDecimal salePrice;

    private BigDecimal actualSale = new BigDecimal("0");

    @TableField(select = false,exist = false)
    private BigDecimal rate;

    @TableField(select = false,exist = false)
    private Integer fbaFirstSize;

    @TableField(select = false,exist = false)
    private Integer estimateSize;
    @TableField(select = false,exist = false)
    private Integer actualSize;
    @TableField(select = false,exist = false)
    private Integer needAddSize;
    @TableField(select = false,exist = false)
    private Integer ks;


    private  String inJson;


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getInJson() {
        return inJson;
    }

    public void setInJson(String inJson) {
        this.inJson = inJson;
    }

    public Integer getKs() {
        return ks;
    }

    public void setKs(Integer ks) {
        this.ks = ks;
    }

    public Integer getNeedAddSize() {
        return needAddSize;
    }

    public void setNeedAddSize(Integer needAddSize) {
        this.needAddSize = needAddSize;
    }

    public Integer getActualSize() {
        return actualSize;
    }

    public void setActualSize(Integer actualSize) {
        this.actualSize = actualSize;
    }

    public Integer getEstimateSize() {
        return estimateSize;
    }

    public void setEstimateSize(Integer estimateSize) {
        this.estimateSize = estimateSize;
    }

    public Integer getFbaFirstSize() {
        return fbaFirstSize;
    }

    public void setFbaFirstSize(Integer fbaFirstSize) {
        this.fbaFirstSize = fbaFirstSize;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getActualSale() {
        return actualSale;
    }

    public void setActualSale(BigDecimal actualSale) {
        this.actualSale = actualSale;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public SaleEntity setSaleId(Integer saleId) {
        this.saleId = saleId;
        return this;
    }
    public String getAsin() {
        return asin;
    }

    public SaleEntity setAsin(String asin) {
        this.asin = asin;
        return this;
    }
    public String getProductName() {
        return productName;
    }

    public SaleEntity setProductName(String productName) {
        this.productName = productName;
        return this;
    }
    public String getSite() {
        return site;
    }

    public SaleEntity setSite(String site) {
        this.site = site;
        return this;
    }
    public String getUser() {
        return user;
    }

    public SaleEntity setUser(String user) {
        this.user = user;
        return this;
    }
    public Date getTimes() {
        return times;
    }

    public SaleEntity setTimes(Date times) {
        this.times = times;
        return this;
    }
    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public SaleEntity setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
        return this;
    }

    @Override
    public String toString() {
        return "SaleEntity{" +
            "saleId=" + saleId +
            ", asin=" + asin +
            ", productName=" + productName +
            ", site=" + site +
            ", user=" + user +
            ", times=" + times +
            ", salePrice=" + salePrice +
        "}";
    }
}
