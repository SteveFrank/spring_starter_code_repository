package com.smart.mybatis.core;


/**
 *
 * 使用MyBatis中最基础的一个类
 * 在该简单版本中包括了最基本的一个核心思路
 * 当开启SqlSession的时候会进行一个DefaultSqlSession的返回
 *
 * 在构造函数中向下传递Configuration的配置文件，在配置文件中包括了
 * Connection、DataSource、mapperElement
 *
 * @author yangqian
 * @date 2021/4/13
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration.connection, configuration.mapperElement);
    }
}
