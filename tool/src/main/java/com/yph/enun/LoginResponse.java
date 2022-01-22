package com.yph.enun;


import com.yph.param.SystemParam;

/**
 * @author Agu
 */
public enum LoginResponse {

    ADMIN_LOGIN(SystemParam.LOGIN_COOKIE_KEY,
            "/math/admin/", "/math/adminLogin.html", "/math/adminLogin.html","/page/buyerRegister.html", "/math/adminLogin.html","/page/shop/buyerAdd.html");
//    ADMIN_PHONE(SystemParam.LOGIN_COOKIE_KEY,
//            "/page/admin/", "/page/adminLogin.html", "/page/adminLogin.html","/page/buyerRegister.html", "/page/adminLogin.html","/page/shop/buyerAdd.html");



    String cookieKey;

    String responseUrl;

    String[] skipUrl;

    String filterUrl;

    LoginResponse(String cookieKey, String filterUrl, String responseUrl, String... skipUrl) {
        this.cookieKey = cookieKey;
        this.skipUrl = skipUrl;
        this.responseUrl = responseUrl;
        this.filterUrl = filterUrl;
    }
    //admin/xx.html/xx/xxx.html
    public static LoginResponse filterUrl(String url) {
       return values()[0];
    }

    public String getCookieKey() {
        return cookieKey;
    }

    public void setCookieKey(String cookieKey) {
        this.cookieKey = cookieKey;
    }

    public String[] getSkipUrl() {
        return skipUrl;
    }

    public void setSkipUrl(String[] skipUrl) {
        this.skipUrl = skipUrl;
    }

    public String getFilterUrl() {
        return filterUrl;
    }

    public void setFilterUrl(String filterUrl) {
        this.filterUrl = filterUrl;
    }

    public String getResponseUrl() {
        return responseUrl;
    }

    public void setResponseUrl(String responseUrl) {
        this.responseUrl = responseUrl;
    }
}
