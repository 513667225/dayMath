package com.math.modules.monday.entity;

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
 * @since 2022-01-17
 */
@TableName("monday")
public class MondayEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "monday_id", type = IdType.AUTO)
    private Integer mondayId;

    private Date times;

    private Integer count;

    private String asin;

    private String site;

    public Integer getMondayId() {
        return mondayId;
    }

    public MondayEntity setMondayId(Integer mondayId) {
        this.mondayId = mondayId;
        return this;
    }
    public Date getTimes() {
        return times;
    }

    public MondayEntity setTimes(Date times) {
        this.times = times;
        return this;
    }
    public Integer getCount() {
        return count;
    }

    public MondayEntity setCount(Integer count) {
        this.count = count;
        return this;
    }
    public String getAsin() {
        return asin;
    }

    public MondayEntity setAsin(String asin) {
        this.asin = asin;
        return this;
    }
    public String getSite() {
        return site;
    }

    public MondayEntity setSite(String site) {
        this.site = site;
        return this;
    }

    @Override
    public String toString() {
        return "MondayEntity{" +
            "mondayId=" + mondayId +
            ", times=" + times +
            ", count=" + count +
            ", asin=" + asin +
            ", site=" + site +
        "}";
    }
}
