package com.math.modules.user.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.math.modules.user.service.IUserService;
import com.math.sdk.core.Config;
import com.math.sdk.core.HttpMethod;
import com.math.sdk.core.HttpRequest;
import com.math.sdk.core.HttpResponse;
import com.math.sdk.entity.Result;
import com.math.sdk.okhttp.AKRestClientBuild;
import com.math.sdk.okhttp.HttpExecutor;
import com.math.sdk.sign.ApiSign;
import com.yph.annotation.Pmap;
import com.yph.entity.UserEntity;
import com.yph.param.RedisParamenter;
import com.yph.param.SystemParam;
import com.yph.redis.service.RedisService;
import com.yph.util.DateUtil;
import com.yph.util.JSONUtils;
import com.yph.util.P;
import com.yph.util.R;
import com.yph.util.utli.JwtUtil;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author
 * @since 2021-09-09
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;


    @Autowired
    RedisService redisService;


    @RequestMapping("/addUser")
    public R addUser(@Pmap P p) throws Exception {
        UserEntity userEntity = p.thisToEntity(UserEntity.class);
        String checkPassword = p.getString("checkPassword");
        if (!checkPassword.equals(userEntity.getPassword())) {
            return R.error("两次输入密码不一致");
        }
        if (userEntity.getPassword().length() < 6) {
            return R.error("密码最小长度为6");
        }
        String username = userEntity.getUsername();
        UserEntity user = userService.getOne(new QueryWrapper<UserEntity>().eq("username", username));
        if (user != null) {
            return R.error("用户已存在");
        }
        userEntity.setCreateTime(new Date());
        userService.save(userEntity);
        return R.success();
    }

    @RequestMapping("/login")
    public R login(@Pmap P p) throws Exception {
        UserEntity one = userService.getOne(new QueryWrapper<UserEntity>().eq("username", p.getString("username")));
        if (one == null) {
            return R.error("用户不存在");
        }
        if (!one.getPassword().equals(p.getString("password"))) {
            return R.error("密码错误");
        }
        String username = "";
        username = one.getUsername();
        String str = JSONUtils.toString(one);
        redisService.set(RedisParamenter.USER_REDIS_LOGIN_KEY + one.getUserId(), str);
        Cookie cookie = new Cookie(SystemParam.LOGIN_COOKIE_KEY, one.getUserId().toString());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        p.getResponse().addCookie(cookie);
        String jwt = JwtUtil.createJWT(UUID.randomUUID().toString(), str);
        Map<String, Object> hash = new HashMap<>();
        hash.put("username", username);
        hash.put("token", jwt);
        return R.success("success").data(hash).set("username", username);
    }


    @RequestMapping("/exit")
    public R exit(@Pmap P p) {
        HttpServletRequest request = p.getRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return R.success();
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(SystemParam.LOGIN_COOKIE_KEY)) {
                redisService.remove(RedisParamenter.USER_REDIS_LOGIN_KEY + cookie.getValue());
            }
        }
        HttpServletResponse response = p.getResponse();
        Cookie cookie = new Cookie(SystemParam.LOGIN_COOKIE_KEY, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return R.success();
    }



}
