package com.math.modules.aging.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.io.Serializable;
import java.util.Date;

public class AgingModel extends BaseRowModel implements Serializable {

    @ExcelProperty(value = "ASIN",index = 0)
    private String asin;
    @ExcelProperty(value = "站点",index = 1)
    private String site;
    @ExcelProperty(value = "安全天数",index = 2)
    private Integer securityDay;



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

    public Integer getSecurityDay() {
        return securityDay;
    }

    public void setSecurityDay(Integer securityDay) {
        this.securityDay = securityDay;
    }


}
