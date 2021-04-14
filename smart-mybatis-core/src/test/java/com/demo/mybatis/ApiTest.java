package com.demo.mybatis;

import com.alibaba.fastjson.JSON;
import com.demo.mybatis.entity.User;
import com.smart.mybatis.core.Resources;
import com.smart.mybatis.core.SqlSession;
import com.smart.mybatis.core.SqlSessionFactory;
import com.smart.mybatis.core.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.Reader;

/**
 * @author yangqian
 * @date 2021/4/14
 */
public class ApiTest {

    @Test
    public void test_queryUserInfoById() {
        String resource = "mybatis-config-datasource.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);

            SqlSession session = sqlMapper.openSession();
            try {
                User user = session.selectOne("com.demo.mybatis.dao.IUserDao.queryUserInfoById", 1L);
                System.out.println(JSON.toJSONString(user));
            } finally {
                session.close();
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
