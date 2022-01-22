package com.yph.annotation;

import com.yph.enun.JurisdictionEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Jurisdiction {


    /**
     * @return 访问所需要的的级别，大于该级别也可以访问
     */
    JurisdictionEnum jurisdictionNumber() default JurisdictionEnum.JURISDICTION_COMMON;


}
