package com.talen.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author 田磊
 * @version 1.0
 * @date 2024/9/16
 * @time 9:38
 */
public class Druid_ {
    @Test
    public void testDruid() throws Exception {
        // 1. 添加druid jar包
        // 2. 添加和配置druid.properties，放在项目的src目录下
        // 3. 创建properties对象，读取配置
        Properties druidProperties = new Properties();
        druidProperties.load(Druid_.class.getClassLoader().getResourceAsStream("druid.properties"));

        // 4. 创建druid数据库连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(druidProperties);
        // 5. 测试500000次获取and关闭连接的耗时
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            Connection connection = dataSource.getConnection();
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("Druid连接池连接500000次耗时 = " + (end - start) + "ms");
    }
}
