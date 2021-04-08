package com.demo.starter.controller;

import com.demo.starter.entity.UserInfo;
import com.middleware.whitelist.annotation.DoWhiteList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangqian
 * @date 2021/4/7
 */
@RestController
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @DoWhiteList(key = "userId", returnJson = "{\"code\":\"1000\",\"info\":\"非白名单可访问用户拦截！\"}")
    @RequestMapping(path = "/api/queryUserInfo", method = RequestMethod.GET)
    public UserInfo queryUserInfo(@RequestParam String userId) {
        LOG.info("查询用户信息, userId : {}", userId);
        return new UserInfo("user Id : " + userId, 20, "上海市徐汇区");
    }


}
