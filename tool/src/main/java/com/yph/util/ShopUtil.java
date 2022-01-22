package com.yph.util;

import com.yph.enun.ShopEnum;
import org.apache.http.HttpRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class ShopUtil {

    public static String shopCookieValue(HttpServletRequest httpReq){
        if (httpReq.getCookies()==null){
            return null;
        }
        for (Cookie cookie : httpReq.getCookies()) {
            if (cookie.getName().equals(ShopEnum.SHOP_ID.getName())){
                return cookie.getValue();
            }
        }
        return null;
    }

}
