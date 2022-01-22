package com.yph.resolvers;

import com.yph.annotation.Pmap;
import com.yph.entity.UserDo;
import com.yph.entity.UserEntity;
import com.yph.param.RedisParamenter;
import com.yph.param.SystemParam;
import com.yph.redis.service.RedisService;
import com.yph.util.JSONUtils;
import com.yph.util.P;
import com.yph.util.utli.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.RequestParamMapMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Spring MVC参数注入时拦截@{@link Pmap} 注解
 *
 * @author Agu
 */
public class PmapResolver extends RequestParamMapMethodArgumentResolver {


    @Autowired
    RedisService redisService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Pmap.class);
    }


    //Spring MVC 处理你参数的结果
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        P p = new P((Map) super.resolveArgument(parameter, mavContainer, webRequest, binderFactory));
        HttpServletResponse nativeResponse = webRequest.getNativeResponse(HttpServletResponse.class);
        HttpServletRequest nativeRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        p.setRequest(nativeRequest);
        p.setResponse(nativeResponse);
        p.removeNullString();
        Cookie[] cookies = nativeRequest.getCookies();
        String userKey = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(SystemParam.LOGIN_COOKIE_KEY)){
                    userKey =  cookie.getValue();
                }
            }
        }
        if (userKey != null) {
//            System.out.println("111111111111");
            UserEntity userDo = JSONUtils.toObject(redisService.get(RedisParamenter.USER_REDIS_LOGIN_KEY+userKey), UserEntity.class);
            p.setUserDo(userDo);
        }else {
//            System.out.println("222222222222222");
            String authHeader = nativeRequest.getHeader("token");
            if (authHeader!=null){
                Claims claims = JwtUtil.parseJWT(authHeader);
                String subject = claims.getSubject();
                UserEntity userDo = JSONUtils.toObject(subject, UserEntity.class);
                p.setUserDo(userDo);
            }
        }

        if(nativeRequest.getAttribute("userId")!=null){
            Object userId = nativeRequest.getAttribute("userId");
            p.put("userId",Integer.valueOf(String.valueOf(userId)));
            p.put("user_id",Integer.valueOf(String.valueOf(userId)));
        }
        Integer limit = p.getInt("limit");
        Integer page = p.getInt("page");
        if (limit != null && page != null)
            p.put("rowIndex", (page - 1) * limit);
//        RequestThreadLocal.REQUEST_THREAD_LOCAL.set(nativeRequest);
        p.batchToInt("limit","page");
        return p;
    }
}
