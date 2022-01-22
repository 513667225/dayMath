package com.yph.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Agu
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Power {

    /**
     * 注解控制权限， 用逗号隔开级别   1,2,3  不区分顺序
     * @return
     */
    String value();

}
