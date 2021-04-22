package com.smart.rpc.spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.Resource;

/**
 * @author yangqian
 * @date 2021/4/20
 */
public class ServerAutoConfiguration implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(ServerAutoConfiguration.class);

    @Resource
    private ServerProperties serverProperties;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("启动Redis模拟注册中心开始");

    }
}
