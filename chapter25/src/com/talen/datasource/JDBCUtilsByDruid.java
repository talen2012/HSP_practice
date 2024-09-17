package com.talen.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author 田磊
 * @version 1.0
 * @date 2024/9/16
 * @time 10:15
 */
public class JDBCUtilsByDruid {
    private static DataSource ds;
    static {
        Properties druidProperties = new Properties();
        try {
            druidProperties.load(JDBCUtilsByDruid.class.getClassLoader().getResourceAsStream("druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(druidProperties);
        } catch (IOException e) {
            throw new RuntimeException("druid.properties配置文件加载失败");
        } catch (Exception e) {
            throw new RuntimeException("创建Druid连接池失败");
        }
    }

    // 从连接池获取一个连接
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    // 将连接放回连接池中
    // Druid连接池，会自动将连接创建的ResultSet和PreparedStatement释放
    public static void close(ResultSet set, PreparedStatement pps, Connection connection) {

        try {
            if (set != null) {
                set.close();
            }
            if (pps != null) {
                pps.close();
            }
            // 注意，Druid连接池对Connection接口中close的实现，只是将连接放回连接池中，而不是关闭连接
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
