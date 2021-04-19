package com.middleware.mybatis.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yangqian
 * @date 2021/4/19
 */
@ConfigurationProperties(prefix = MybatisProperties.MYBATIS_PREFIX)
public class MybatisProperties {

    public static final String MYBATIS_PREFIX = "mybatis.datasource";
    /**
     * com.mysql.jdbc.Driver
     */
    private String driver;
    /**
     * jdbc:mysql://127.0.0.1:3306/bugstack?useUnicode=true
     */
    private String url;
    /**
     * root
     */
    private String username;
    /**
     * admin123
     */
    private String password;
    /**
     * classpath*:mapper/*.xml
     */
    private String mapperLocations;
    /**
     * cn.bugstack.middleware.test.infrastructure.dao
     */
    private String baseDaoPackage;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMapperLocations() {
        return mapperLocations;
    }

    public void setMapperLocations(String mapperLocations) {
        this.mapperLocations = mapperLocations;
    }

    public String getBaseDaoPackage() {
        return baseDaoPackage;
    }

    public void setBaseDaoPackage(String baseDaoPackage) {
        this.baseDaoPackage = baseDaoPackage;
    }


}
