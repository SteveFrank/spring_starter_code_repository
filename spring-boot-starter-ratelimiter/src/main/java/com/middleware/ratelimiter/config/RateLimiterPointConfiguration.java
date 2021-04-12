package com.middleware.ratelimiter.config;

import com.middleware.ratelimiter.DoRateLimiterPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RateLimiterPointConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public DoRateLimiterPoint point() {
        return new DoRateLimiterPoint();
    }

}
