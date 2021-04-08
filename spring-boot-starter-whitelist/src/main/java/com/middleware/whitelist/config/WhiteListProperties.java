package com.middleware.whitelist.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * 从自定义配置中读取配置信息
 *
 * @author yangqian
 * @date 2021/4/6
 */
@ConfigurationProperties(prefix = "middleware.config.whitelist")
public class WhiteListProperties {

    private String users;

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
}
