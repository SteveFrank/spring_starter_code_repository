package com.starter.common.lib.ibatis;

/**
 * @author frankq
 * @date 2021/9/8
 */
public interface SqlSessionFactory {

    SqlSession openSession();

}
