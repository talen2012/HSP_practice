package com.talen.preparedstatement;

import com.talen.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author 田磊
 * @version 1.0
 * @date 2024/9/14
 * @time 14:59
 */
public class PreparedStatement_ {
    @SuppressWarnings({"all"})
    public static void main(String[] args) throws Exception {
        // 让用户输入管理员和密码
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入管理员名称：");
        String admin_name = scanner.nextLine();
        System.out.print("请输入管理员密码：");
        String admin_pwd = scanner.nextLine(); // nextLine()碰到空格和制表符会继续读取，直到碰到回车

        // 1. 获取连接
        Connection connection = JDBCUtils.getConnection();
        // // 设置自动提交为false
        connection.setAutoCommit(false);
        // 2. 创建PreparedStatement, 在创建时就传入sql语句，与Statement在执行时再传入不同
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "select * from admin where `name` = ? and pwd = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, admin_name);
            preparedStatement.setString(2, admin_pwd);

            // 注意这个时候不能再传入sql了，传入就会直接运行这个sql
            resultSet = preparedStatement.executeQuery();
            // 如果有查询到就登录成功
            if (resultSet.next()) {
                System.out.println("登录成功！");
            } else {
                System.out.println("登录失败！");
            }
            // 执行DML语句
            int rows = 0;
            // 添加3条数据
            String sql2 = "insert into admin values ('x', '7'), ('y', '8'), ('z', '9')";
            rows = preparedStatement.executeUpdate(sql2);
            System.out.println(rows == 3 ? "添加成功" : "添加失败");

            // 修改tom的记录，将名字修改为king
            String sql3 = "update admin set `name` = 'king' where `name` = 'tom'";
            rows = preparedStatement.executeUpdate(sql3);
            System.out.println(rows == 1 ? "修改成功" : "修改失败");

            // 删除'y'记录
            String sql4 = "delete from admin where `name` = 'y'";
            rows = preparedStatement.executeUpdate(sql4);
            System.out.println(rows == 1? "删除成功" : "删除失败");

            // 显示所有的记录
            String sql5 = "select * from admin";
            ResultSet resultset5 = preparedStatement.executeQuery(sql5);
            while (resultset5.next()) {
                String name = resultset5.getString("name");
                String pwd = resultset5.getString("pwd");
                System.out.println(name + "\t" + pwd);
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            JDBCUtils.close(resultSet, preparedStatement, connection);
        }
    }
}
