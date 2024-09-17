package com.talen.datasource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 田磊
 * @version 1.0
 * @date 2024/9/16
 * @time 11:42
 */
public class DBUtils_USE {

    //使用apache-DBUtils 工具类 + druid 完成对表的crud操作
    @Test
    public void testQueryMany() { // 返回结果是多行
        Connection connection = null;
        try {
            // 1. 获取连接
            connection = JDBCUtilsByDruid.getConnection();
            // 2. 使用 DBUtils 类和接口 , 先引入DBUtils 相关的jar , 加入到本Project
            // 3. 创建 QueryRunner
            QueryRunner queryRunner = new QueryRunner();
            // 4. 组织sql语句，并使用DBUtils的方法来执行，返回ArrayList
            String sql = "select * from actor where id >= ?";
            // 老韩解读
            //(1) query 方法就是执行sql 语句，得到resultset ---封装到 --> ArrayList 集合中
            //(2) 返回集合
            //(3) connection: 连接
            //(4) sql : 执行的sql语句
            //(5) new BeanListHandler<>(Actor.class): 在将resultset -> Actor 对象 -> 封装到 ArrayList
            //    底层使用反射机制 去获取Actor 类的属性，然后进行封装
            //(6) 1 就是给 sql 语句中的? 赋值，可以有多个值，因为是可变参数Object... params
            //(7) 底层得到的resultset ,会在query 关闭, 关闭PreparedStatment

            // 编译器会根据Actor.class自动推断出new BeanListHandler<>(Actor.class) 为
            // new BeanListHandler<Actor>(Actor.class)，于是泛型T就被指定为了Actor

            List<Actor> list =
                    queryRunner.query(connection, sql, new BeanListHandler<>(Actor.class), 1);
            System.out.println("输出集合的信息");
            for (Actor actor : list) {
                System.out.println(actor);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            // ResultSet和PreparedStatement是在query中创建和释放的，不用再释放
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    @Test
    public void testQuerySingle() { // 返回结果是单行
        Connection connection = null;
        try {
            // 1. 获取连接
            connection = JDBCUtilsByDruid.getConnection();
            // 2. 使用 DBUtils 类和接口 , 先引入DBUtils 相关的jar , 加入到本Project
            // 3. 创建 QueryRunner
            QueryRunner queryRunner = new QueryRunner();
            // 4. 组织sql语句，并使用DBUtils的方法来执行，返回ArrayList
            String sql = "select * from actor where id = ?";

            Actor actor =
                    queryRunner.query(connection, sql, new BeanHandler<>(Actor.class), 2);
            System.out.println("输出单行的信息");
            System.out.println(actor);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            // ResultSet和PreparedStatement是在query中创建和释放的，不用再释放
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    @Test
    public void testQueryScalar() { // 返回结果是多行
        Connection connection = null;
        try {
            // 1. 获取连接
            connection = JDBCUtilsByDruid.getConnection();
            // 2. 使用 DBUtils 类和接口 , 先引入DBUtils 相关的jar , 加入到本Project
            // 3. 创建 QueryRunner
            QueryRunner queryRunner = new QueryRunner();
            // 4. 组织sql语句，并使用DBUtils的方法来执行，返回ArrayList
            String sql = "select name from actor where id >= ?";

            Object obj = queryRunner.query(connection, sql, new ScalarHandler(), 1);
            System.out.println("输出单行单列的信息");
            System.out.println(obj);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            // ResultSet和PreparedStatement是在query中创建和释放的，不用再释放
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    @Test
    public void testQueryDML() { // 返回结果是多行
        Connection connection = null;
        try {
            // 1. 获取连接
            connection = JDBCUtilsByDruid.getConnection();
            // 2. 使用 DBUtils 类和接口 , 先引入DBUtils 相关的jar , 加入到本Project
            // 3. 创建 QueryRunner
            QueryRunner queryRunner = new QueryRunner();
            // 4. 组织sql语句，并使用DBUtils的方法来执行，返回ArrayList
            String sql = "update actor set name = ? where id = ?";

            // 返回受影响的行数
            int rows = queryRunner.update(connection, sql, "周星驰", 1);
            System.out.println(rows > 0 ? "执行成功" : "执行没有影响到表");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            // ResultSet和PreparedStatement是在query中创建和释放的，不用再释放
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }
}
