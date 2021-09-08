package com.starter.common.lib.example.controller;

import com.starter.common.lib.annotation.CheckWhiteList;
import com.starter.common.lib.example.dao.IStudentDao;
import com.starter.common.lib.example.domain.Student;
import com.starter.common.lib.example.vo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author frankq
 * @date 2021/9/8
 */
@RestController
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Resource
    private IStudentDao studentDao;


    /**
     * 通过：http://localhost:8081/api/queryUserInfo?userId=aaa
     * 拦截：http://localhost:8081/api/queryUserInfo?userId=123
     */
    @CheckWhiteList(key = "userId", returnJson = "{\"code\":\"403\",\"info\":\"非白名单可访问用户拦截！\"}")
    @RequestMapping(path = "/api/queryUserInfo", method = RequestMethod.GET)
    public UserInfo queryUserInfo(@RequestParam String userId) {
        LOG.info("查询用户信息，userId：{}", userId);
        return new UserInfo("username:" + userId, 19, "四川省成都市天府科技园12-0000");
    }

    @RequestMapping(path = "/api/queryUserInfoById", method = RequestMethod.GET)
    public Student queryUserInfoById(){
        return studentDao.queryStudentByName("zhangsan");
    }

}
