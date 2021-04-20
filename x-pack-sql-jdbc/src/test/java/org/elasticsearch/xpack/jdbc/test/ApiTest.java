package org.elasticsearch.xpack.jdbc.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author yangqian
 * @date 2021/4/20
 */
public class ApiTest {

    private static final Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_sql() throws Exception {
        // 创建请求
        String address = "jdbc:es://http://127.0.0.1:9200";
        Connection connection = DriverManager.getConnection(address, new Properties());
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery("SELECT id, userId, userNickName, userHead, userPassword, createTime, updateTime FROM user");
        while (results.next()) {
            logger.info("id：{} userId：{} useNickName：{} userHead：{} userPassword：{} updateTime：{}",
                    results.getString("id"),
                    results.getString("userNickName"),
                    results.getString("userHead"),
                    results.getString("userPassword"),
                    results.getString("createTime"),
                    results.getString("updateTime"));
        }
    }

}
