package com.yph.enun;

public enum  CountryEnum {
    US("美国","美国","加拿大","墨西哥","巴西") ,
    JP("日本","日本") ,
    ER("欧洲","德国","意大利","西班牙","法国","荷兰","瑞典","波兰") ,
    UK("英国","英国") ,
    AU("澳洲","澳洲") ,
    UAE("阿联酋","阿联酋") ,
    SG("新加坡","新加坡") ,
    SA("沙特阿拉伯","沙特阿拉伯") ,
    TUR("土耳其","土耳其") ,
    ID("印度","印度") ;


    private  String root;

    private  String[] countrys;

    CountryEnum(String root,String... countrys) {
        this.root = root;
        this.countrys = countrys;
    }


    public  static  String getRoot(String counrty){
        for (CountryEnum value : values()) {
            for (String country : value.countrys) {
                if (country.equals(counrty)){
                    return value.root;
                }
            }
        }
        return "美国";
    }

}
