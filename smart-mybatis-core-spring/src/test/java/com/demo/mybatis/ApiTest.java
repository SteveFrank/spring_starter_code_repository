package com.demo.mybatis;

import com.alibaba.fastjson.JSON;
import com.demo.mybatis.dao.IUserDao;
import com.demo.mybatis.entity.User;
import com.smart.mybatis.core.Resources;
import com.smart.mybatis.core.SqlSession;
import com.smart.mybatis.core.SqlSessionFactory;
import com.smart.mybatis.core.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

/**
 * @author yangqian
 * @date 2021/4/14
 */
public class ApiTest {

    private static final Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_ClassPathXmlApplicationContext() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("spring-application.xml");
        IUserDao userDao = beanFactory.getBean("IUserDao", IUserDao.class);
        User user = userDao.queryUserInfoById(1L);
        logger.info("测试结果：{}", JSON.toJSONString(user));
    }

    @Test
    public void test_yml() throws IOException {
        Reader reader = Resources.getResourceAsReader("application.yml");
        Map map = (Map) new Yaml().load(reader);
        System.out.println(map.toString());
        System.out.println(((Map)((Map)map.get("mybatis")).get("datasource")).get("basePackage"));
    }



}
