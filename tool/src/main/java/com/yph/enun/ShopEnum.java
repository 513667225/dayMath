package com.yph.enun;

public enum ShopEnum {
    SHOP_ID("BOUTIQUE_MALL"),
    SYS_SHOP_ID("SYS_SHOP_ID");


    private final String name;

    ShopEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
