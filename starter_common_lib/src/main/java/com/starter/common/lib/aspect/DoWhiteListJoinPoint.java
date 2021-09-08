package com.starter.common.lib.aspect;

import com.alibaba.fastjson.JSON;
import com.starter.common.lib.annotation.CheckWhiteList;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author frankq
 * @date 2021/9/8
 */
@Aspect
@Component
public class DoWhiteListJoinPoint {

    private static final Logger LOG = LoggerFactory.getLogger(DoWhiteListJoinPoint.class);

    @Resource
    private String whiteListConfig;

    @Pointcut("@annotation(com.starter.common.lib.annotation.CheckWhiteList)")
    public void aopPoint() {
    }

    @Around("aopPoint()")
    public Object doRouter(ProceedingJoinPoint joinPoint) throws Throwable {
        // 从注释的方法上获取内容
        Method method = getMethod(joinPoint);
        CheckWhiteList whiteList = method.getAnnotation(CheckWhiteList.class);
        // 获取字段值
        String keyValue = getFiledValue(whiteList.key(), joinPoint.getArgs());
        LOG.info("middleware whitelist handler method：{} value：{}", method.getName(), keyValue);
        String[] split = whiteListConfig.split(",");
        // 白名单过滤
        for (String str : split) {
            if (keyValue.equals(str)) {
                return joinPoint.proceed();
            }
        }
        // 拦截
        return returnObject(whiteList, method);
    }

    private Method getMethod(JoinPoint jp) throws NoSuchMethodException {
        Signature sig = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) sig;
        return jp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

    // 返回对象
    private Object returnObject(CheckWhiteList whiteList, Method method) throws IllegalAccessException, InstantiationException {
        Class<?> returnType = method.getReturnType();
        String returnJson = whiteList.returnJson();
        if ("".equals(returnJson)) {
            return returnType.newInstance();
        }
        return JSON.parseObject(returnJson, returnType);
    }

    // 获取属性值
    private String getFiledValue(String filed, Object[] args) {
        String filedValue = null;
        for (Object arg : args) {
            try {
                if (null == filedValue || "".equals(filedValue)) {
                    filedValue = BeanUtils.getProperty(arg, filed);
                } else {
                    break;
                }
            } catch (Exception e) {
                if (args.length == 1) {
                    return args[0].toString();
                }
            }
        }
        return filedValue;
    }


}
