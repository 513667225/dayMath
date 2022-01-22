package com.math.modules.account.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2021-12-27
 */
@TableName("account")
public class AccountEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "account_id", type = IdType.AUTO)
    private Integer accountId;

    private Integer uid;

    private String realname;

    private String username;

    private String mobile;

    private String email;

    private String role;

    private String seller;

    public Integer getAccountId() {
        return accountId;
    }

    public AccountEntity setAccountId(Integer accountId) {
        this.accountId = accountId;
        return this;
    }
    public Integer getUid() {
        return uid;
    }

    public AccountEntity setUid(Integer uid) {
        this.uid = uid;
        return this;
    }
    public String getRealname() {
        return realname;
    }

    public AccountEntity setRealname(String realname) {
        this.realname = realname;
        return this;
    }
    public String getUsername() {
        return username;
    }

    public AccountEntity setUsername(String username) {
        this.username = username;
        return this;
    }
    public String getMobile() {
        return mobile;
    }

    public AccountEntity setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }
    public String getEmail() {
        return email;
    }

    public AccountEntity setEmail(String email) {
        this.email = email;
        return this;
    }
    public String getRole() {
        return role;
    }

    public AccountEntity setRole(String role) {
        this.role = role;
        return this;
    }
    public String getSeller() {
        return seller;
    }

    public AccountEntity setSeller(String seller) {
        this.seller = seller;
        return this;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
            "accountId=" + accountId +
            ", uid=" + uid +
            ", realname=" + realname +
            ", username=" + username +
            ", mobile=" + mobile +
            ", email=" + email +
            ", role=" + role +
            ", seller=" + seller +
        "}";
    }
}
