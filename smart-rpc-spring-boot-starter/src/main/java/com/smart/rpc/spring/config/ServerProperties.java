package com.smart.rpc.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * 连接 注册中心 地址与端口的配置
 *
 * @author yangqian
 * @date 2021/4/20
 */
@ConfigurationProperties("rpc.server")
public class ServerProperties {

    /**
     * 注册中心地址
     */
    private String host;
    /**
     * 注册中心端口
     */
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
