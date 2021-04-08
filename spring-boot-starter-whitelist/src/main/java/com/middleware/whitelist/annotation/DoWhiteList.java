package com.middleware.whitelist.annotation;

import java.lang.annotation.*;

/**
 * @author yangqian
 * @date 2021/4/6
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoWhiteList {

    String key() default "";

    String returnJson() default "";

}
