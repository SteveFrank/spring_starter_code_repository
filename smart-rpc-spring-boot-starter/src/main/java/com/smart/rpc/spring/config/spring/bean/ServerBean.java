package com.smart.rpc.spring.config.spring.bean;

import com.smart.rpc.spring.config.ServerConfig;
import com.smart.rpc.spring.registry.RedisRegistryCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author yangqian
 * @date 2021/4/23
 */
public class ServerBean extends ServerConfig implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(ServerBean.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 启动注册中心
        logger.info("启动注册中心 ... ...");
        RedisRegistryCenter.init(host, port);
        logger.info("启动注册中心完成 host:{}, port:{}", host, port);

        // 初始化服务端
        logger.info("初始化生产端服务 ... ...");
        
    }
}
