package com.middleware.hystrix.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yangqian
 * @date 2021/4/9
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoHystrix {

    /**
     * 失败的结果json
     * @return
     */
    String returnJson() default "";

    /**
     * 超时熔断
     * 方法的调用安全范围时长，一般指的是 TP99、TP999 的调用稳定值
     * @return
     */
    int timeoutValue() default 0;

}
