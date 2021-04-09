package com.middleware.hystrix;

import com.middleware.hystrix.annotation.DoHystrix;
import com.middleware.hystrix.service.IValveService;
import com.middleware.hystrix.service.impl.HystrixValveImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author yangqian
 * @date 2021/4/9
 */
@Aspect
public class DoHystrixPoint {

    @Pointcut("@annotation(com.middleware.hystrix.annotation.DoHystrix)")
    public void aopPoint() {

    }

    @Around("aopPoint() && @annotation(doGovern)")
    public Object doRouter(ProceedingJoinPoint joinPoint, DoHystrix doGovern) throws Throwable {
        // 此处会有线程不安全的问题，正式代码中需要注意调整
        IValveService valveService = new HystrixValveImpl();
        return valveService.access(joinPoint, getMethod(joinPoint), doGovern, joinPoint.getArgs());
    }

    private Method getMethod(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return joinPoint.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

}
