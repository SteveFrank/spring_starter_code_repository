package com.middleware.ratelimiter.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.RateLimiter;
import com.middleware.ratelimiter.Constants;
import com.middleware.ratelimiter.annotation.DoRateLimiter;
import com.middleware.ratelimiter.service.IValueService;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 *
 * 实际的限流逻辑
 *
 * @author yangqian
 * @date 2021/4/12
 */
public class RateLimiterValveImpl implements IValueService {

    @Override
    public Object access(ProceedingJoinPoint joinPoint, Method method, DoRateLimiter doRateLimiter, Object[] args)
            throws Throwable {

        // 1、判断限流是否开启
        if (0 == doRateLimiter.permitsPerSecond()) {
            return joinPoint.proceed();
        }

        // 获取类名
        String clazzName = joinPoint.getTarget().getClass().getName();
        // 获取方法名称
        String methodName = method.getName();

        String key = clazzName + "." + methodName;

        if (null == Constants.rateLimiterMap.get(key)) {
            Constants.rateLimiterMap.put(key, RateLimiter.create(doRateLimiter.permitsPerSecond()));
        }

        RateLimiter rateLimiter = Constants.rateLimiterMap.get(key);
        if (rateLimiter.tryAcquire()) {
            return joinPoint.proceed();
        }

        return JSON.parseObject(doRateLimiter.returnJson(), method.getReturnType());
    }
}
