package com.math.modules.now.entity;

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
 * @since 2021-11-15
 */
@TableName("`now`")
public class NowEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "now_id", type = IdType.AUTO)
    private Integer nowId;

    private String asin;

    private String site;

    private Integer nowCount;


    private Integer mondayCount;

    public Integer getMondayCount() {
        return mondayCount;
    }

    public void setMondayCount(Integer mondayCount) {
        this.mondayCount = mondayCount;
    }

    public Integer getNowId() {
        return nowId;
    }

    public NowEntity setNowId(Integer nowId) {
        this.nowId = nowId;
        return this;
    }
    public String getAsin() {
        return asin;
    }

    public NowEntity setAsin(String asin) {
        this.asin = asin;
        return this;
    }
    public String getSite() {
        return site;
    }

    public NowEntity setSite(String site) {
        this.site = site;
        return this;
    }
    public Integer getNowCount() {
        return nowCount;
    }

    public NowEntity setNowCount(Integer nowCount) {
        this.nowCount = nowCount;
        return this;
    }

    @Override
    public String toString() {
        return "NowEntity{" +
            "nowId=" + nowId +
            ", asin=" + asin +
            ", site=" + site +
            ", nowCount=" + nowCount +
        "}";
    }
}
