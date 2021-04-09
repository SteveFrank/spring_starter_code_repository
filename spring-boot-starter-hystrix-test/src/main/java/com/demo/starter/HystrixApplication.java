package com.demo.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yangqian
 * @date 2021/4/9
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.demo.starter.*", "com.middleware.hystrix.*"})
public class HystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication.class, args);
    }

}
