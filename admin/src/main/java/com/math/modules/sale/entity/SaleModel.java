package com.math.modules.sale.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SaleModel extends BaseRowModel implements Serializable {

    @ExcelProperty(value = "ASIN",index = 0)
    private String asin;
    @ExcelProperty(value = "品名",index = 1)
    private String name;
    @ExcelProperty(value = "站点",index = 2)
    private String site;
    @ExcelProperty(value = "负责人",index = 3)
    private String user;
    @ExcelProperty(value = "日期",index = 4,format = "yyyy/MM/dd")
    private Date times;
    @ExcelProperty(value = "销量",index = 5)
    private BigDecimal sale;
    @ExcelProperty(value = "实际销量",index = 6)
    private BigDecimal aaSale;

    public BigDecimal getAaSale() {
        return aaSale;
    }

    public void setAaSale(BigDecimal aaSale) {
        this.aaSale = aaSale;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
    }

    public BigDecimal getSale() {
        return sale;
    }

    public void setSale(BigDecimal sale) {
        this.sale = sale;
    }
}
