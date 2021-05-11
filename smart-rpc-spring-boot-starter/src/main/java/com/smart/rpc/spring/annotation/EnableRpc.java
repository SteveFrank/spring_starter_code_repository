package com.smart.rpc.spring.annotation;

import com.smart.rpc.spring.config.ServerAutoConfiguration;
import com.smart.rpc.spring.config.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yangqian
 * @date 2021/4/29
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({ServerAutoConfiguration.class})
@EnableConfigurationProperties(ServerProperties.class)
@ComponentScan("com.smart.rpc.spring.*")
public @interface EnableRpc {

}
