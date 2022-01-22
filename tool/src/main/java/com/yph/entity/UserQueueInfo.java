package com.yph.entity;

/**
 * @author Agu
 */
public class UserQueueInfo {



    private int  userQueueEnum;

    private Long money;

    private  Long createTime;


    public UserQueueInfo() {
    }

    public UserQueueInfo( int userQueueEnum , Long money, Long createTime) {
        this.userQueueEnum = userQueueEnum;
        this.createTime = createTime;
        this.money = money;
    }


    public int getUserQueueEnum() {
        return userQueueEnum;
    }

    public void setUserQueueEnum(int userQueueEnum) {
        this.userQueueEnum = userQueueEnum;
    }


    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
