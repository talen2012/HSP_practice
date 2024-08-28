package com.talen.properties;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Properties02 {
    /**
     * 使用Properties类来读取mysql.properties文件
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // 1. 创建Properties对象
        Properties properties = new Properties();
        // 2. 加载指定配置文件
        properties.load(new FileReader("e:/Java_practice/HSP_practice/chapter19/mysql.properties"));
        // 3. 把k-v显示在控制台
        properties.list(System.out);
        // 4. 根据key获取对应的值
        String usr = properties.getProperty("user");
        String pwd = properties.getProperty("pwd");
        System.out.println("用户名：" + usr);
        System.out.println("密码" + pwd);

    }
}
