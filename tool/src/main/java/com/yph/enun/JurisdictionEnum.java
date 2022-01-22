package com.yph.enun;

/**
 * @author LC
 */
public enum JurisdictionEnum {

    JURISDICTION_COMMON(1,"COMMON"),
    JURISDICTION_ADMIN(0,"ADMIN")
    ;

    private  int code;

    private String cont;


      JurisdictionEnum(int code, String cont){
          this.code = code;
          this.cont = cont;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }
}
