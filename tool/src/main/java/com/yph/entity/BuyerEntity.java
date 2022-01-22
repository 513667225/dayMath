package com.yph.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2021-05-31
 */
@TableName("buyer")
public class BuyerEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "buyer_id", type = IdType.AUTO)
    private Integer buyerId;

    /**
     * 国家
     */
    private Integer countryId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 图片佣金
     */
    private BigDecimal imgCommission;

    /**
     * 文字佣金
     */
    private BigDecimal wordCommission;

    /**
     * 视频佣金
     */
    private BigDecimal videoCommission;

    /**
     * 联系方式
     */
    private String contactDetails;

    /**
     * 关联的用户ID
     *
     */
    private  Integer userId;
    /**
     *
     *
     */
    private  String profile;

    /**
     * 联系方式2
     */
    private String contactDetails2;

    /**
     * facebook
     */
    private  String facebook;

    /**
     * 微信
     */
    private String weChat;

    /**
     * telegram
     */
    private  String telegram;

    /**
     * paypal
     *
     */
    private  String paypal;

    /**
     * payoneer
     */
    private  String payoneer;

    /**
     * 备注
     */
    private  String remark;

    public Integer getBuyerId() {
        return buyerId;
    }

    public BuyerEntity setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
        return this;
    }
    public Integer getCountryId() {
        return countryId;
    }

    public BuyerEntity setCountryId(Integer countryId) {
        this.countryId = countryId;
        return this;
    }
    public String getName() {
        return name;
    }

    public BuyerEntity setName(String name) {
        this.name = name;
        return this;
    }
    public Integer getLevel() {
        return level;
    }

    public BuyerEntity setLevel(Integer level) {
        this.level = level;
        return this;
    }
    public BigDecimal getImgCommission() {
        return imgCommission;
    }

    public BuyerEntity setImgCommission(BigDecimal imgCommission) {
        this.imgCommission = imgCommission;
        return this;
    }
    public BigDecimal getWordCommission() {
        return wordCommission;
    }

    public BuyerEntity setWordCommission(BigDecimal wordCommission) {
        this.wordCommission = wordCommission;
        return this;
    }
    public BigDecimal getVideoCommission() {
        return videoCommission;
    }

    public BuyerEntity setVideoCommission(BigDecimal videoCommission) {
        this.videoCommission = videoCommission;
        return this;
    }
    public String getContactDetails() {
        return contactDetails;
    }

    public BuyerEntity setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
        return this;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getContactDetails2() {
        return contactDetails2;
    }

    public void setContactDetails2(String contactDetails2) {
        this.contactDetails2 = contactDetails2;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getPaypal() {
        return paypal;
    }

    public void setPaypal(String paypal) {
        this.paypal = paypal;
    }

    public String getPayoneer() {
        return payoneer;
    }

    public void setPayoneer(String payoneer) {
        this.payoneer = payoneer;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "BuyerEntity{" +
            "buyerId=" + buyerId +
            ", countryId=" + countryId +
            ", name=" + name +
            ", level=" + level +
            ", imgCommission=" + imgCommission +
            ", wordCommission=" + wordCommission +
            ", videoCommission=" + videoCommission +
            ", contactDetails=" + contactDetails +
        "}";
    }
}
