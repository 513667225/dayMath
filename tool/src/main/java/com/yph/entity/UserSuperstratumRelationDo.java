package com.yph.entity;

public class UserSuperstratumRelationDo {
    private Integer user_id;
    private Integer topRefereeId;
    private Integer role;
    private Integer userReferrer;
    private String user_name;
    private String relation;

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public String toString() {
        return "UserSuperstratumRelationDo{" +
                "user_id=" + user_id +
                ", topRefereeId=" + topRefereeId +
                ", userReferrer=" + userReferrer +
                '}';
    }

    public Integer getUserReferrer() {
        return userReferrer;
    }

    public void setUserReferrer(Integer userReferrer) {
        this.userReferrer = userReferrer;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getTopRefereeId() {
        return topRefereeId;
    }

    public void setTopRefereeId(Integer topRefereeId) {
        this.topRefereeId = topRefereeId;
    }



    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
