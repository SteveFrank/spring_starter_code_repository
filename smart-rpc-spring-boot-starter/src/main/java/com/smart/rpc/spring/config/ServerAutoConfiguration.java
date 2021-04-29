package com.smart.rpc.spring.config;

import com.smart.rpc.spring.domain.LocalServerInfo;
import com.smart.rpc.spring.registry.RedisRegistryCenter;
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
        RedisRegistryCenter.init(serverProperties.getHost(), serverProperties.getPort());
        logger.info("启动Redis模拟注册中心完成，{} {}", serverProperties.getHost(), serverProperties.getPort());

        logger.info("初始化生产端服务开始");
//        ServerSocket serverSocket = new ServerSocket(applicationContext);
//        Thread thread = new Thread(serverSocket);
//        thread.start();
//        while (!serverSocket.isActiveSocketServer()) {
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException ignore) {
//            }
//        }
        logger.info("初始化生产端服务完成 {} {}", LocalServerInfo.LOCAL_HOST, LocalServerInfo.LOCAL_PORT);
    }

}
