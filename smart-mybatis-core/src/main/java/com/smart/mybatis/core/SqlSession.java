package com.smart.mybatis.core;

import java.util.List;

/**
 *
 * 定义针对数据库操作的查询接口，分为查询一个结果和查询多个结果，同时包括有参数和没有参数的两个方法
 *
 * @author yangqian
 * @date 2021/4/13
 */
public interface SqlSession {

    <T> T selectOne(String statement);

    <T> T selectOne(String statement, Object parameter);

    <T> List<T> selectList(String statement);

    <T> List<T> selectList(String statement, Object paramter);

    void close();

}
