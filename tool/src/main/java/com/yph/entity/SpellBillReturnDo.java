package com.yph.entity;

import java.util.List;

/**
 * @author xinge
 */
public class SpellBillReturnDo {

    //拼团记录ID
    private String bid;

    //中奖人员信息
    private List<UserEntity> list;

    //时间
    private String create_time;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public List<UserEntity> getList() {
        return list;
    }

    public void setList(List<UserEntity> list) {
        this.list = list;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "SpellBillReturnDo{" +
                "bid='" + bid + '\'' +
                ", list=" + list +
                ", create_time='" + create_time + '\'' +
                '}';
    }
}
