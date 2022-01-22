package com.yph.entity;

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
@TableName("aging")
public class AgingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "aging_id", type = IdType.AUTO)
    private Integer agingId;


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

    /**
     * 安全天数
     */
    private Integer securityDay;

    /**
     * 快递天数
     */
    private Integer expressDay;

    /**
     * 空运天数
     */
    private Integer airDay;

    /**
     * 海运天数
     */
    private Integer seaDay;

    public Integer getAgingId() {
        return agingId;
    }

    public AgingEntity setAgingId(Integer agingId) {
        this.agingId = agingId;
        return this;
    }

    public Integer getSecurityDay() {
        return securityDay;
    }

    public AgingEntity setSecurityDay(Integer securityDay) {
        this.securityDay = securityDay;
        return this;
    }
    public Integer getExpressDay() {
        return expressDay;
    }

    public AgingEntity setExpressDay(Integer expressDay) {
        this.expressDay = expressDay;
        return this;
    }
    public Integer getAirDay() {
        return airDay;
    }

    public AgingEntity setAirDay(Integer airDay) {
        this.airDay = airDay;
        return this;
    }
    public Integer getSeaDay() {
        return seaDay;
    }

    public AgingEntity setSeaDay(Integer seaDay) {
        this.seaDay = seaDay;
        return this;
    }

    @Override
    public String toString() {
        return "AgingEntity{" +
            "agingId=" + agingId +
            ", securityDay=" + securityDay +
            ", expressDay=" + expressDay +
            ", airDay=" + airDay +
            ", seaDay=" + seaDay +
        "}";
    }
}
