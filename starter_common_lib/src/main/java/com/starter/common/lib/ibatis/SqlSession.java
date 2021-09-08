package com.starter.common.lib.ibatis;

import java.util.List;

/**
 * @author frankq
 * @date 2021/9/8
 */
public interface SqlSession {

    <T> T selectOne(String statement);

    <T> T selectOne(String statement, Object parameter);

    <T> List<T> selectList(String statement);

    <T> List<T> selectList(String statement, Object parameter);

    void close();
    
}
