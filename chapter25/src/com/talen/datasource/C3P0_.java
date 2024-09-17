package com.talen.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author 田磊
 * @version 1.0
 * @date 2024/9/15
 * @time 17:10
 */
public class C3P0_ {
    @Test
    public void testC3P0_01() throws SQLException, PropertyVetoException {
        // 1. 创建一个数据源对象
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        // 2. 通过配置文件获取相关连接的信息
        Properties properties = new Properties();
        String user, password, url, driver;
        try (InputStream input = C3P0_.class.getClassLoader().getResourceAsStream("jdbc.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find jdbc.properties");
            }
            properties.load(input);
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            driver = properties.getProperty("driver");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 3. 给数据源 comboPooledDataSource 设置相关的参数
        // // 注意：连接管理是由 comboPooledDataSource 来管理
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setDriverClass(driver);

        // 4. 设置DataSource连接池的参数
        // // 初始化连接数
        comboPooledDataSource.setInitialPoolSize(10);
        // // 最大连接数
        comboPooledDataSource.setMaxPoolSize(50);
        long start = System.currentTimeMillis();
        // // 测试连接池的效率 5000次
        for (int i = 0; i < 5000; i++) {
            Connection connection = comboPooledDataSource.getConnection();
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时 = " + (end - start));
    }

    @Test
    public void testC3P0_02() throws SQLException {
        // 1. 从配置文件创建一个DataSource对象
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("talen");
        // 2. 无需再配置参数
        long start = System.currentTimeMillis();
        // // 测试连接池的效率 500000次
        for (int i = 0; i < 500000; i++) {
            Connection connection = comboPooledDataSource.getConnection();
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("C3P0 500000次连接耗时 = " + (end - start));

    }
}
