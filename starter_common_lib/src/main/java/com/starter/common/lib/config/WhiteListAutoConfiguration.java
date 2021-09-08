package com.starter.common.lib.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author frankq
 * @date 2021/9/8
 */
@Configuration
@ConditionalOnClass(WhiteListProperties.class)
@EnableConfigurationProperties(WhiteListProperties.class)
@ComponentScan(basePackages = "com.starter.common.lib")
public class WhiteListAutoConfiguration {

    @Bean("whiteListConfig")
    @ConditionalOnMissingBean
    public String whiteListConfig(WhiteListProperties properties) {
        return properties.getUsers();
    }

}
