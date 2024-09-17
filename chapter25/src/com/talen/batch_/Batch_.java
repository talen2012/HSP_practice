package com.talen.batch_;

import com.talen.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author 田磊
 * @version 1.0
 * @date 2024/9/15
 * @time 16:20
 */
public class Batch_ {
    public static void main(String[] args) throws SQLException {
    }

    @Test
    public void testNoBatch() throws SQLException {
        // 1. 获取连接
        Connection connection = JDBCUtils.getConnection();
        // 2. 执行插入
        PreparedStatement preparedStatement = connection.prepareStatement("insert into admin2 values (null, ?, ?)");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString(1, "john" + i);
            preparedStatement.setString(2, "" + i);
            preparedStatement.executeUpdate();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时 = " + (end - start) + "ms");
        // 3. 释放资源
        JDBCUtils.close(null, preparedStatement, connection);
    }

    @Test
    public void testBatch() throws SQLException {
        // 1. 获取连接
        Connection connection = JDBCUtils.getConnection();
        // 2. 执行插入
        PreparedStatement preparedStatement = connection.prepareStatement("insert into admin2 values (null, ?, ?)");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString(1, "john" + i);
            preparedStatement.setString(2, "" + i);
            preparedStatement.addBatch();
            // 每1000个语句执行一次
            if ((i + 1) % 1000 == 0) {
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时 = " + (end - start) + "ms");
        // 3. 释放资源
        JDBCUtils.close(null, preparedStatement, connection);
    }
}
