package com.yph.filter;

import com.yph.entity.UserEntity;
import com.yph.enun.LoginResponse;
import com.yph.param.RedisParamenter;
import com.yph.redis.service.RedisService;
import com.yph.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Agu
 */
public class LoginHtmlFilter implements Filter {


    @Autowired
    RedisService redisService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        HttpServletResponse response = ((HttpServletResponse) servletResponse);
        String s = request.getRequestURI();
        LoginResponse loginResponse = LoginResponse.filterUrl(s);
        if (loginResponse != null) {
            for (String s1 : loginResponse.getSkipUrl()) {
                if (s.equals(s1)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
        }
        Cookie[] cookies = request.getCookies();
        if (cookies==null){
            response.sendRedirect(loginResponse.getResponseUrl());
            return;
        }
        for (Cookie cookie : cookies) {
            String cookieKey = loginResponse.getCookieKey();
            if (cookieKey.equals(cookie.getName())) {
                if (redisService.get(RedisParamenter.USER_REDIS_LOGIN_KEY+cookie.getValue()) != null) {
                    if (JSONUtils.toObject(redisService.get(RedisParamenter.USER_REDIS_LOGIN_KEY + cookie.getValue()), UserEntity.class) != null) {
                        filterChain.doFilter(servletRequest, servletResponse);
                        return;
                    }
                }
            }
        }

        response.sendRedirect(loginResponse.getResponseUrl());

    }

    @Override
    public void destroy() {

    }


}