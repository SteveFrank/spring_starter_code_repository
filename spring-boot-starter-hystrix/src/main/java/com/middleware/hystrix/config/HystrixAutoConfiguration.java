package com.middleware.hystrix.config;

import com.middleware.hystrix.DoHystrixPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangqian
 * @date 2021/4/9
 */
@Configuration
public class HystrixAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public DoHystrixPoint point(){
        return new DoHystrixPoint();
    }

}
