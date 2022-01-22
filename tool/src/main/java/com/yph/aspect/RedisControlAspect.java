package com.yph.aspect;

import com.yph.annotation.RedisControl;
import com.yph.redis.service.RedisService;
import com.yph.util.R;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Agu
 */
@Component
@Aspect
public class RedisControlAspect {

    @Autowired
    RedisService redisService;


    @Around("@annotation(com.yph.annotation.RedisControl)")
    public  Object redisControl(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        String name = method.getDeclaringClass().getName() +"."+ method.getName();
        if (!redisService.getRedisTemplate().hasKey(name)) {
            redisService.set(name,"0");
        }
        if (redisService.get(name).equals("0")) {
            proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());

        }
        return R.error("此方法暂时关闭");
    }

}
