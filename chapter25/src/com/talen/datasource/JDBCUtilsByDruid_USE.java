package com.talen.datasource;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author 田磊
 * @version 1.0
 * @date 2024/9/16
 * @time 10:26
 */
public class JDBCUtilsByDruid_USE {
    @Test
    public void testSelect() {
        // 1. 预先创建变量
        Connection connection = null;
        PreparedStatement pps = null;
        ResultSet rs = null;

        // 2. 组织一个sql
        String sql = "select * from actor where id = ?";

        // 3. 创建连接和流
        try {
            connection = JDBCUtilsByDruid.getConnection();
            pps = connection.prepareStatement(sql);
            pps.setInt(1, 1); // 给？赋值
            // 4. 执行，得到结果集
            rs = pps.executeQuery();
            // 5. 遍历和显示结果集
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                Date bornDate = rs.getDate("borndate");
                String phone = rs.getString("phone");
                System.out.println(id + "\t" + name + "\t" + sex + "\t" + bornDate + "\t" + phone);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            JDBCUtilsByDruid.close(rs, pps, connection);
        }
    }
}
