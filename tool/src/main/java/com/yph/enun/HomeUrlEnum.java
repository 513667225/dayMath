package com.yph.enun;

/**
 * @author Agu
 */
public enum HomeUrlEnum {

    USER(1,"/page/userHome.html"),SHOP(2,"/page/shopHome.html"),ADMIN(3,"/page/adminHome.html");

    private  int level;

    private  String url;


    HomeUrlEnum(int level, String url) {
        this.level = level;
        this.url = url;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public static String getUrlByLevel(int level){
        for (HomeUrlEnum value : values()) {
            if (value.level==level){
                return value.url;
            }
        }
        return null;
    }
}
