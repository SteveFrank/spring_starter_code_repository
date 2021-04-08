package com.demo.starter;

import com.middleware.whitelist.annotation.EnableWhiteList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yangqian
 * @date 2021/4/7
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.demo.starter.*", "com.middleware.whitelist.*"})
@EnableWhiteList
public class WhiteListApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhiteListApplication.class, args);
    }

}
