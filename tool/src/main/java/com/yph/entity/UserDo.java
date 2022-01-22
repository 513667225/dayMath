package com.yph.entity;

/**
 * @author Agu
 */
public class UserDo {

    private UserEntity userEntity;

    private BuyerEntity buyerEntity;

    private ShopEntity shopEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public BuyerEntity getBuyerEntity() {
        return buyerEntity;
    }

    public void setBuyerEntity(BuyerEntity buyerEntity) {
        this.buyerEntity = buyerEntity;
    }


    public ShopEntity getShopEntity() {
        return shopEntity;
    }

    public void setShopEntity(ShopEntity shopEntity) {
        this.shopEntity = shopEntity;
    }
}
