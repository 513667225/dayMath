package com.yph.entity;

import org.elasticsearch.index.VersionType;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author Agu
 */
@Document(indexName = "spellbill",type = "_doc",shards = 1, replicas =0,createIndex = false,useServerConfiguration = true,versionType = VersionType.EXTERNAL)
public class SpellBillDo {

    //ID
    @Id
    private String bid;


    //拼团的人 前3个是中奖的人
    @Field(type = FieldType.Text)
    private String groupPeople;

    //拼团的人 前3个是中奖的人
    @Field(type = FieldType.Integer)
    private Integer groupSize;

    //创建时间
    @Field(type = FieldType.Text)
    private String create_time;


    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }


    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public SpellBillDo() {
    }

    public String getGroupPeople() {
        return groupPeople;
    }

    public void setGroupPeople(String groupPeople) {
        this.groupPeople = groupPeople;
    }

    public Integer getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(Integer groupSize) {
        this.groupSize = groupSize;
    }

    public SpellBillDo(String bid, String groupPeople, Integer groupSize, String create_time) {
        this.bid = bid;
        this.groupPeople = groupPeople;
        this.groupSize = groupSize;
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "SpellBillDo{" +
                "bid='" + bid + '\'' +
                ", groupPeople='" + groupPeople + '\'' +
                ", create_time='" + create_time + '\'' +
                '}';
    }
}
