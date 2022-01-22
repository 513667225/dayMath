package com.yph.entity;

/**
 * @author Agu
 */
public class SpellQueueInfo {


    private long createTime;

    private  int renew;

    private Long money;

    public SpellQueueInfo(){

    }

    public SpellQueueInfo( long createTime, int renew, Long money) {
        this.createTime  = createTime;
        this.renew = renew;
        this.money= money;
    }


    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getRenew() {
        return renew;
    }

    public void setRenew(int renew) {
        this.renew = renew;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }


    @Override
    public String toString() {
        return "SpellQueueInfo{" +
                ", createTime=" + createTime +
                ", renew=" + renew +
                ", money=" + money +
                '}';
    }
}
