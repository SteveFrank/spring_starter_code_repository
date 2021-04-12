package com.demo.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yangqian
 * @date 2021/4/12
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.demo.starter.*", "com.middleware.ratelimiter.*"})
public class RateLimiterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RateLimiterApplication.class, args);
    }

}
