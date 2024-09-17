package com.talen._resultset;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;

/**
 * @author 田磊
 * @version 1.0
 * @date 2024/9/14
 * @time 10:34
 */
public class ResultSet_ {
    @SuppressWarnings({"all"})
    public static void main(String[] args) throws Exception{
        // 1. 读取properties配置文件并解析
        Properties jdbcConnectInfo = new Properties();
        jdbcConnectInfo.load(new FileInputStream("chapter25/src/jdbc.properties"));
        String user = jdbcConnectInfo.getProperty("user");
        String password = jdbcConnectInfo.getProperty("password");
        String url = jdbcConnectInfo.getProperty("url");
        String driverName = jdbcConnectInfo.getProperty("driver");

        Class<?> driverClass = Class.forName(driverName);
        // 2. 建立和server的连接
        Connection jdbcConnect = DriverManager.getConnection(url, user, password);
        // 3. 获得Statement
        Statement statement = jdbcConnect.createStatement();
        // 4. 执行query SQL, 获得ResultSet对象
        String sql = "select id, `name`, sex, borndate from actor";
        ResultSet resultSet = statement.executeQuery(sql);

        // 5. 使用while取出数据
        // ResultSet对象的row属性是一个RawDataStatic对象，其属性index初始值为-1
        while (resultSet.next()) { // 直接访问下一行
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String sex = resultSet.getString(3);
            Date date = resultSet.getDate(4);
            System.out.println(id + "\t" + name + "\t" + sex + "\t" + date);
        }

        // 关闭资源
        resultSet.close();
        statement.close();
        jdbcConnect.close();
    }
}
