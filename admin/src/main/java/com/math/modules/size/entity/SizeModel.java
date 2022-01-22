package com.math.modules.size.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.io.Serializable;

public class SizeModel extends BaseRowModel implements Serializable {

    @ExcelProperty(value = "ASIN",index = 0)
    private String asin;
    @ExcelProperty(value = "站点",index = 1)
    private String site;
    @ExcelProperty(value = "个数",index = 2)
    private Integer count;




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


}
