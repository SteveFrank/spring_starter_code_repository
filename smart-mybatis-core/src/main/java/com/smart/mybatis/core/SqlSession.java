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

    /**
     * 带有入参的单条数据查询
     * @param statement
     * @param parameter
     * @param <T>
     * @return
     */
    <T> T selectOne(String statement, Object parameter);

    <T> List<T> selectList(String statement);

    /**
     * 带有入参的多条数据查询
     * @param statement
     * @param parameter
     * @param <T>
     * @return
     */
    <T> List<T> selectList(String statement, Object parameter);

    void close();

}
