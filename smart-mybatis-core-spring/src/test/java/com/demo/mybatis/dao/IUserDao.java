package com.demo.mybatis.dao;

import com.demo.mybatis.entity.User;

import java.util.List;

/**
 * @author yangqian
 * @date 2021/4/14
 */
public interface IUserDao {

    User queryUserInfoById(Long id);

    List<User> queryUserList(String userNickName);

}
