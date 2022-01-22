package com.math.modules.updateSale.entity;

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
 * @since 2021-12-21
 */
@TableName("update_sale")
public class UpdateSaleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "update_id", type = IdType.AUTO)
    private Integer updateId;

    private Date times;

    private String user;

    private BigDecimal beforeVal;

    private BigDecimal afterVal;

    private String asin;

    private  String site;

    private  Integer saleId;

    private Date times1;

    public Date getTimes1() {
        return times1;
    }

    public void setTimes1(Date times1) {
        this.times1 = times1;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
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

    public Integer getUpdateId() {
        return updateId;
    }

    public UpdateSaleEntity setUpdateId(Integer updateId) {
        this.updateId = updateId;
        return this;
    }
    public Date getTimes() {
        return times;
    }

    public UpdateSaleEntity setTimes(Date times) {
        this.times = times;
        return this;
    }
    public String getUser() {
        return user;
    }

    public UpdateSaleEntity setUser(String user) {
        this.user = user;
        return this;
    }
    public BigDecimal getBeforeVal() {
        return beforeVal;
    }

    public UpdateSaleEntity setBeforeVal(BigDecimal beforeVal) {
        this.beforeVal = beforeVal;
        return this;
    }
    public BigDecimal getAfterVal() {
        return afterVal;
    }

    public UpdateSaleEntity setAfterVal(BigDecimal afterVal) {
        this.afterVal = afterVal;
        return this;
    }

    @Override
    public String toString() {
        return "UpdateSaleEntity{" +
            "updateId=" + updateId +
            ", times=" + times +
            ", user=" + user +
            ", beforeVal=" + beforeVal +
            ", afterVal=" + afterVal +
        "}";
    }
}
