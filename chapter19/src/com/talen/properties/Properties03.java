package com.talen.properties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Properties03 {
    /**
     * 使用Properties保存配置
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // 1. 创建Properties对象
        Properties properties = new Properties();
        // 2. setProperties
        properties.setProperty("charset", "utf-8");
        properties.setProperty("user", "汤姆");
        properties.setProperty("pwd", "abc111");
        // 3. 存储
        properties.store(new FileOutputStream("e:/Java_practice/HSP_practice/chapter19/mysql03.properties"), "comments");
        System.out.println("保存成功...");

        Properties p = new Properties();
        p.load(new FileInputStream("e:/Java_practice/HSP_practice/chapter19/mysql03.properties"));
        System.out.println(p.getProperty("user"));
    }
}
