package com.smart.mybatis.core;

/**
 * @author yangqian
 * @date 2021/4/13
 */
public interface SqlSessionFactory {
    SqlSession openSession();
}
