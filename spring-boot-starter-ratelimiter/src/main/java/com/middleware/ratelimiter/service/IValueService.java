package com.middleware.ratelimiter.service;

import com.middleware.ratelimiter.annotation.DoRateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @author yangqian
 * @date 2021/4/12
 */
public interface IValueService {

    Object access(ProceedingJoinPoint joinPoint, Method method, DoRateLimiter doRateLimiter, Object[] args) throws Throwable;

}
