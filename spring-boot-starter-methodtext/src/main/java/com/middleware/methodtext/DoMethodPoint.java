package com.middleware.methodtext;

import com.alibaba.fastjson.JSON;
import com.middleware.methodtext.annotation.DoMethodExt;
import org.aspectj.lang.JoinPoint;
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
 * @date 2021/4/12
 */
@Aspect
@Component
public class DoMethodPoint {

    @Pointcut("@annotation(com.middleware.methodtext.annotation.DoMethodExt)")
    public void aopPoint() {

    }

    @Around("aopPoint()")
    public Object doRouter(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取内容
        Method method = getMethod(joinPoint);
        DoMethodExt doMethodExt = method.getAnnotation(DoMethodExt.class);
        // 获取拦截方法
        String methodName = doMethodExt.method();
        // 功能处理
        Method methodExt = getClass(joinPoint).getMethod(methodName, method.getParameterTypes());
        Class<?> returnType = methodExt.getReturnType();

        // 判断方法返回类型
        if (!returnType.getName().equals("boolean")) {
            throw new RuntimeException("annotation @DoMethodExt set method：" + methodName + " returnType is not boolean");
        }
        // 拦截判断正常，继续
        boolean invoke = (boolean) methodExt.invoke(joinPoint.getThis(), joinPoint.getArgs());
        // 返回结果
        return invoke ? joinPoint.proceed() : JSON.parseObject(doMethodExt.returnJson(), method.getReturnType());
    }

    private Method getMethod(JoinPoint jp) throws NoSuchMethodException {
        Signature sig = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) sig;
        return jp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

    private Class<? extends Object> getClass(JoinPoint jp) throws NoSuchMethodException {
        return jp.getTarget().getClass();
    }

}
