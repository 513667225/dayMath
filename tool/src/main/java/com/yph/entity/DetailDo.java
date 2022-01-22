package com.yph.entity;


import org.elasticsearch.index.VersionType;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Document(indexName = "detail",type = "_doc",shards = 1, replicas =0,createIndex = false,useServerConfiguration = true,versionType = VersionType.EXTERNAL)
public class DetailDo {

    //ID
    @Id
    private String did;


    //用户身份标识(此记录属于谁)
    @Field(type = FieldType.Integer)
    private int userKey;


    //操作类型
    @Field(type = FieldType.Integer)
    private int operationType;

    //金额类型
    @Field(type = FieldType.Integer)
    private  int money_type;

    //金额源头用户ID
    @Field(type = FieldType.Integer)
    private  int raw_user_id;

    //金额流向用户ID
    @Field(type = FieldType.Integer)
    private  int to_user_id;

    //金额数
    @Field(type = FieldType.Double)
    private  double money_number;

    //创建时间
    @Field(type = FieldType.Text)
    private String create_time;

    //交易流水号
    @Field(type = FieldType.Keyword)
    private String serial_number;

    //说明
    @Field(type = FieldType.Keyword)
    private String explain;

    //其他
    @Field(type = FieldType.Keyword)
    private String rests;

    //收入还是支出 (0收入 1支出)
    @Field(type = FieldType.Integer)
    private int marking;


    public DetailDo() {
    }

    /**
     * @param did   //id
     * @param userKey          此记录属于哪个用户
     * @param operationType   //操作类型
     * @param money_type   //金额类型
     * @param raw_user_id  //金额源头用户ID
     * @param to_user_id  //金额流向用户ID
     * @param money_number  //金额数
     * @param create_time  //创建时间
     * @param serial_number  //交易流水号
     * @param explain  //说明
     * @param rests  //其他
     *  @param marking //收入还是支出 (0收入 1支出)
     */
    public DetailDo(String did, int userKey,int operationType, int money_type, int raw_user_id, int to_user_id, double money_number, String create_time,String serial_number,String explain,String rests,int marking) {
        this.did = did;
        this.userKey=userKey;
        this.operationType = operationType;
        this.money_type = money_type;
        this.raw_user_id = raw_user_id;
        this.to_user_id = to_user_id;
        this.money_number = money_number;
        this.create_time = create_time;
        this.serial_number= serial_number;
        this.explain=explain;
        this.rests=rests;
        this.marking=marking;
    }

    @Override
    public String toString() {
        return "DetailDo{" +
                "did='" + did + '\'' +
                ", userKey=" + userKey +
                ", operationType=" + operationType +
                ", money_type=" + money_type +
                ", raw_user_id=" + raw_user_id +
                ", to_user_id=" + to_user_id +
                ", money_number=" + money_number +
                ", create_time='" + create_time + '\'' +
                ", serial_number='" + serial_number + '\'' +
                ", explain='" + explain + '\'' +
                ", rests='" + rests + '\'' +
                ", marking=" + marking +
                '}';
    }

    public int getUserKey() {
        return userKey;
    }

    public void setUserKey(int userKey) {
        this.userKey = userKey;
    }

    public int getMarking() {
        return marking;
    }

    public void setMarking(int marking) {
        this.marking = marking;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getRests() {
        return rests;
    }

    public void setRests(String rests) {
        this.rests = rests;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public int getOperationType() {
        return operationType;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }

    public int getMoney_type() {
        return money_type;
    }

    public void setMoney_type(int money_type) {
        this.money_type = money_type;
    }

    public int getRaw_user_id() {
        return raw_user_id;
    }

    public void setRaw_user_id(int raw_user_id) {
        this.raw_user_id = raw_user_id;
    }

    public int getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(int to_user_id) {
        this.to_user_id = to_user_id;
    }

    public double getMoney_number() {
        return money_number;
    }

    public void setMoney_number(double money_number) {
        this.money_number = money_number;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
