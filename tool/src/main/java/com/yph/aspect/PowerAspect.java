//package com.yph.aspect;
//
//import com.yph.annotation.Pmap;
//import com.yph.annotation.Power;
//import com.yph.entity.UserDo;
//import com.yph.entity.UserEntity;
//import com.yph.util.P;
//import com.yph.util.R;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//
///**
// * @author Agu
// */
//@Component
//@Aspect
//public class PowerAspect {
//
//    @Around("@annotation(com.yph.annotation.Power)")
//    @Order(-1)
//    public Object power(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
//        P p = null;
//        Method method = signature.getMethod();
//        for (int i = 0; i < method.getParameters().length; i++) {
//            if (method.getParameters()[i].isAnnotationPresent(Pmap.class)) {
//                p = (P) proceedingJoinPoint.getArgs()[i];
//            }
//        }
//        Power annotation = method.getAnnotation(Power.class);
//        String value = annotation.value();
//        UserEntity userDo = p.getUserDo();
//        if (userDo==null){
//            return R.error("用户登陆信息过期，请重新登录");
//        }
//        Integer level = userDo.getUserEntity().getLevel();
//        if (value.indexOf(level.toString())!=-1) {
//            return  proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
//        }
//        return R.error("当前权限不够执行此逻辑");
//    }
//}
