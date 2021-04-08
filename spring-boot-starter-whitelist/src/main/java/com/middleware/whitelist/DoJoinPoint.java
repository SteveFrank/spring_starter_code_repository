package com.middleware.whitelist;

import com.alibaba.fastjson.JSON;
import com.middleware.whitelist.annotation.DoWhiteList;
import org.apache.commons.beanutils.BeanUtils;
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
 * @author yangqian
 * @date 2021/4/6
 */
@Aspect
@Component
public class DoJoinPoint {

    private static final Logger LOG = LoggerFactory.getLogger(DoJoinPoint.class);

    @Resource(name = "whiteListConfig")
    private String whiteListConfig;

    @Pointcut("@annotation(com.middleware.whitelist.annotation.DoWhiteList)")
    public void aopPoint() {

    }

    @Around("aopPoint()")
    public Object doRouter(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 获取对应的内容
        Method method = getMethod(proceedingJoinPoint);
        LOG.info("method is : {}", method.getName());
        // 获取method上面的注解标签
        DoWhiteList whiteList = method.getAnnotation(DoWhiteList.class);
        // 获取字段内容值
        String keyValue = getFiledValue(whiteList.key(), proceedingJoinPoint.getArgs());
        LOG.info("middleware whitelist handler method:{}, value:{}", method.getName(), keyValue);
        if (null == keyValue || "".equalsIgnoreCase(keyValue)) {
            return proceedingJoinPoint.proceed();
        }

        String[] split = whiteListConfig.split(",");

        // 白名单过滤
        for (String str : split) {
            if (keyValue.equalsIgnoreCase(str)) {
                LOG.info("white list passed!");
                return proceedingJoinPoint.proceed();
            }
        }

        // 拦截
        return returnObject(whiteList, method);
    }

    private Method getMethod(ProceedingJoinPoint proceedingJoinPoint) throws NoSuchMethodException {
        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return proceedingJoinPoint.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

    /**
     * 获取属性值
     * @param filed
     * @param args
     * @return
     */
    private String getFiledValue(String filed, Object[] args) {
        String filedValue = null;
        for (Object arg : args) {
            try {
                if (null == filedValue || "".equalsIgnoreCase(filedValue)) {
                    filedValue = BeanUtils.getProperty(arg, filed);
                    LOG.info("filed is : {}, filedValue is : {}", filed, filedValue);
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

    // 返回对象
    private Object returnObject(DoWhiteList whiteList, Method method) throws IllegalAccessException, InstantiationException {
        Class<?> returnType = method.getReturnType();
        String returnJson = whiteList.returnJson();
        if ("".equalsIgnoreCase(returnJson)) {
            return returnType.newInstance();
        }
        return JSON.parseObject(returnJson, returnType);
    }

}
