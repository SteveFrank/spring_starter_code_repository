package com.middleware.ratelimiter;

import com.middleware.ratelimiter.annotation.DoRateLimiter;
import com.middleware.ratelimiter.service.IValueService;
import com.middleware.ratelimiter.service.impl.RateLimiterValveImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author yangqian
 * @date 2021/4/12
 */
@Aspect
public class DoRateLimiterPoint {

    @Pointcut("@annotation(com.middleware.ratelimiter.annotation.DoRateLimiter)")
    public void aopPoint() {

    }

    @Around("aopPoint() && @annotation(doRateLimiter)")
    public Object doRouter(ProceedingJoinPoint joinPoint, DoRateLimiter doRateLimiter) throws Throwable {
        IValueService valueService = new RateLimiterValveImpl();
        return valueService.access(joinPoint, getMethod(joinPoint), doRateLimiter, joinPoint.getArgs());
    }

    private Method getMethod(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return joinPoint.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

}
