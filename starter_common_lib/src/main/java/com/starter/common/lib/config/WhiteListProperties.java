package com.starter.common.lib.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author frankq
 * @date 2021/9/8
 */
@ConfigurationProperties("check.whitelist")
public class WhiteListProperties {

    private String users;

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

}
