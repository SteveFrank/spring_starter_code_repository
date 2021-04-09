package com.middleware.hystrix.service;

import com.middleware.hystrix.annotation.DoHystrix;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @author yangqian
 * @date 2021/4/9
 */
public interface IValveService {

    Object access(ProceedingJoinPoint joinPoint, Method method, DoHystrix doHystrix, Object[] args) throws Exception;

}
