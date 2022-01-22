package com.math.modules.out.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.io.Serializable;
import java.util.Date;

public class OutModel extends BaseRowModel implements Serializable {

    @ExcelProperty(value = "时间",index = 0,format = "yyyy-MM-dd")
    private Date dateTime;
    @ExcelProperty(value = "ASIN",index = 1)
    private String asin;
    @ExcelProperty(value = "PARENTASIN",index = 2)
    private String parentasin;
    @ExcelProperty(value = "MSKU",index = 3)
    private String msku;
    @ExcelProperty(value = "SKU",index = 4)
    private String sku;
    @ExcelProperty(value = "标题",index = 5)
    private String title;
    @ExcelProperty(value = "品名",index = 6)
    private String name;
    @ExcelProperty(value = "负责人",index = 7)
    private String user;
    @ExcelProperty(value = "分类",index = 8)
    private String type;
    @ExcelProperty(value = "品牌",index = 9)
    private String label;
    @ExcelProperty(value = "店铺",index = 10)
    private String shop;
    @ExcelProperty(value = "国家",index = 11)
    private String country;
    @ExcelProperty(value = "FBA可售",index = 12)
    private Integer fba;
    @ExcelProperty(value = "销量",index = 13)
    private Integer saleVal;
    @ExcelProperty(value = "订单量",index = 14)
    private Integer orderCount;
    @ExcelProperty(value = "销售额",index = 15)
    private String xx;
    @ExcelProperty(value = "code",index = 16)
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentasin() {
        return parentasin;
    }

    public void setParentasin(String parentasin) {
        this.parentasin = parentasin;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getMsku() {
        return msku;
    }

    public void setMsku(String msku) {
        this.msku = msku;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getFba() {
        return fba;
    }

    public void setFba(Integer fba) {
        this.fba = fba;
    }

    public Integer getSaleVal() {
        return saleVal;
    }

    public void setSaleVal(Integer saleVal) {
        this.saleVal = saleVal;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }
}
