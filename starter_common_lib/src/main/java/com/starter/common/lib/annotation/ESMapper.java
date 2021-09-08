package com.starter.common.lib.annotation;

import java.lang.annotation.*;

/**
 * @author frankq
 * @date 2021/9/8
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
public @interface ESMapper {
}
