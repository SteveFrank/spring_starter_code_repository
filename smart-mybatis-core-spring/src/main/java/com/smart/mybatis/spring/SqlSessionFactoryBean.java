package com.smart.mybatis.spring;

import com.smart.mybatis.core.Resources;
import com.smart.mybatis.core.SqlSessionFactory;
import com.smart.mybatis.core.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.Reader;

/**
 * 第一步，处理SqlSessionFactoryBean
 * 实现 Initializing Bean 主要用于加载mybatis的相关内容
 * 解析xml、构造SqlSession、链接数据库等
 *
 * FactoryBean 主要是三个方法 getObject()\getObjectType()\isSingleton()
 *
 * @author yangqian
 * @date 2021/4/14
 */
public class SqlSessionFactoryBean implements FactoryBean<SqlSessionFactory>, InitializingBean {

    private String resource;
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void afterPropertiesSet() throws Exception {
        try (Reader reader = Resources.getResourceAsReader(resource)) {
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public SqlSessionFactory getObject() throws Exception {
        return sqlSessionFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return sqlSessionFactory.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
