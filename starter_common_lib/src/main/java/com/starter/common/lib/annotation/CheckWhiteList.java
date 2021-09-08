package com.starter.common.lib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author frankq
 * @date 2021/9/8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CheckWhiteList {

    String key() default "";

    /**
     * 被白名单拦截后的返回值
     * @return
     */
    String returnJson() default "";

}
