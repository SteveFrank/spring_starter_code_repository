package com.smart.rpc.spring.config;

/**
 * @author yangqian
 * @date 2021/4/20
 */
public class ServerConfig {
    /**
     * 注册中心地址
     */
    protected String host;
    /**
     * 注册中心端口
     */
    protected int port;

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
