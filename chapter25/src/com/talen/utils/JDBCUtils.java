package com.talen.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author 田磊
 * @version 1.0
 * @date 2024/9/15
 * @time 14:37
 */
public class JDBCUtils {
    private static String user;
    private static String password;
    private static String url;
    private static String driver;

    // 静态代码块中完成对连接信息的初始化
    static {
        Properties info = new Properties();
        try (InputStream input = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find jdbc.properties");
            }
            info.load(input);
            user = info.getProperty("user");
            password = info.getProperty("password");
            url = info.getProperty("url");
            driver = info.getProperty("driver");

            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // 功能：获取可用的连接
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // 1. 将编译异常转为运行时异常
            // 2. 调用者可以选择捕获该异常，或者默认处理，即抛出
            throw new RuntimeException(e);
        }
    }

    // 功能：释放资源，不需要释放的资源输入null
    public static void close (ResultSet set, Statement statement, Connection connection) {
        try {
            if (set != null) {
                set.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
