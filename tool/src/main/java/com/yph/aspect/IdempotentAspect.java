//package com.yph.aspect;
//
//import com.yph.annotation.Idempotent;
//import com.yph.annotation.Pmap;
//import com.yph.entity.UserDo;
//import com.yph.entity.UserEntity;
//import com.yph.redis.service.RedisService;
//import com.yph.util.P;
//import com.yph.util.R;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author Agu
// */
//@Component
//@Aspect
//public class IdempotentAspect {
//
//    @Autowired
//    RedisService redisService;
//
//    //通过P 拿到用户ID
//    @Around("@annotation(com.yph.annotation.Idempotent)")
//    @Order(-1)
//    public Object idempotent(ProceedingJoinPoint proceedingJoinPoint) throws Exception{
//        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
//        P p = null;
//        Method method = signature.getMethod();
//        Idempotent idempotent=method.getAnnotation(Idempotent.class);
//        for (int i = 0; i < method.getParameters().length; i++) {
//            if (method.getParameters()[i].isAnnotationPresent(Pmap.class)) {
//                p = (P) proceedingJoinPoint.getArgs()[i];
//            }
//        }
//        UserEntity userDo = p.getUserDo();
//        String value = idempotent.value();
//        if (value.equals("112")) {
//            value = method.getName();
//        }
//        String userId = "";
//        if (userDo==null||userDo.getUserEntity()==null||userDo.getUserEntity().getUserId()==null){
//            if (idempotent.key().length==0){
//                try {
//                    return   proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
//                } catch (Throwable throwable) {
//                    throwable.printStackTrace();
//                }
//            }else {
//                for (String s : idempotent.key()) {
//                    userId += p.getString(s)+"_";
//                }
//            }
//        }
//      if (userDo!=null){
//          userId = userDo.getUserEntity().getUserId()+"";
//      }
//        String redisKey = value+userId;//方法名+userID  = RedisKey
//        if (redisService.getRedisTemplate().hasKey(redisKey)){
//            return R.error("当前操作正在执行中，请勿重复执行");
//        }
//        redisService.getValueOperations().set(redisKey,"1",5, TimeUnit.MINUTES);
//        try {
//            Object proceed = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
//            redisService.remove(redisKey);
//            return  proceed ;
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//            redisService.remove(redisKey);
//          throw new Exception(throwable==null? "系统繁忙": throwable.getMessage());
//        }
//
//    }
//
//}
