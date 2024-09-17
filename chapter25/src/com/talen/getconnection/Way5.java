package com.talen.getconnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author 田磊
 * @version 1.0
 * @date 2024/9/14
 * @time 9:04
 */
public class Way5 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        Properties jdbcConnectInfo = new Properties();
        jdbcConnectInfo.load(new FileInputStream("chapter25/src/jdbc.properties"));
        String user = jdbcConnectInfo.getProperty("user");
        String password = jdbcConnectInfo.getProperty("password");
        String url = jdbcConnectInfo.getProperty("url");
        String driverName = jdbcConnectInfo.getProperty("driver");
        System.out.println(user + " " + password + " " + url + " " + driverName);
        Class<?> driverClass = Class.forName(driverName);

        // 使用DriverManager统一管理，可以不注册driver
        // Constructor<?> driverConstructor = driverClass.getConstructor();
        // Driver driver = (Driver) driverConstructor.newInstance();
        // DriverManager.registerDriver(driver);

        // JDK1.5之后使用了jdbc4, 不再需要显式调用Class.forName(), 但建议还是写上，信息比较明确
        Connection jdbcConnect = DriverManager.getConnection(url, jdbcConnectInfo);

        // 执行SQL
        // Statement用于执行静态SQL语句
        Statement statement = jdbcConnect.createStatement();
        String sql1 = "create table `news` ( id INT PRIMARY KEY, content VARCHAR(128) NOT NULL DEFAULT '')";
        String sql2 = "insert into `news` values (1, '1'), (2, '2'), (3, '3'), (4, '4'), (5, '5')";
        String sql3 = "update `news` set content = 'some' where id = 1";
        String sql4 = "delete from `news` where id = 3";

        // executeUpdate()执行SQL，返回受影响的行数
        int rows = 0;
        rows = statement.executeUpdate(sql1);
        System.out.println("受影响的行数：" + rows);

        rows = statement.executeUpdate(sql2);
        System.out.println("受影响的行数：" + rows);
        rows = statement.executeUpdate(sql3);
        System.out.println("受影响的行数：" + rows);
        rows = statement.executeUpdate(sql4);
        System.out.println("受影响的行数：" + rows);

        // 关闭资源
        statement.close();
        jdbcConnect.close();
    }
}
