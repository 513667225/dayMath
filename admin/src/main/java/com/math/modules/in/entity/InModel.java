package com.math.modules.in.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.io.Serializable;
import java.util.Date;

public class InModel extends BaseRowModel implements Serializable {

    @ExcelProperty(value = "ASIN",index = 0)
    private String asin;
    @ExcelProperty(value = "站点",index = 1)
    private String site;
    @ExcelProperty(value = "个数",index = 2)
    private Integer count;
    @ExcelProperty(value = "shipmentID",index = 3)
    private String shipmentId;
    @ExcelProperty(value = "日期",index = 4)
    private Date times;
    @ExcelProperty(value = "编号",index = 5)
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }
}
