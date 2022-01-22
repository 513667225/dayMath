package com.yph.entity;

/**
 * @author Agu
 */
public class AllowanceCache {

    int user_id;

    double rate;

    public AllowanceCache() {
    }

    public AllowanceCache(int user_id, double rate) {
        this.user_id = user_id;
        this.rate = rate;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
