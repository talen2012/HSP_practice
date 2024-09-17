package com.talen.dao_.dao;

import com.talen.dao_.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 田磊
 * @version 1.0
 * @date 2024/9/16
 * @time 15:26
 * 基于druid连接池+DBUtils实现通用的CRUD
 */
public class BasicDAO<T> {
    private QueryRunner qr = new QueryRunner();

    // dml方法，针对任意的表
    public int update(String sql, Object... params) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qr.update(connection, sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e); // 编译异常->运行异常
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    /**
     * 查询多条记录
     * @param sql 查询语句，可以用?占位符
     * @param clazz 传入一个类的Class对象，比如Actor.class，用于反射获取所有字段，记录
     *              一条记录的信息
     * @param params 传入？具体的值，可以是多个
     * @return 根据clazz，返回ArrayList<xxx>
     */
    public List<T> queryMulti(String sql, Class<T> clazz, Object... params) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qr.query(connection, sql, new BeanListHandler<T>(clazz), params);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    // 查询单条记录
    public T querySingle(String sql, Class<T> clazz, Object... params) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qr.query(connection, sql, new BeanHandler<T>(clazz), params);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    // 查询单条记录的单列
    public Object queryScalar(String sql, Object... params) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qr.query(connection, sql, new ScalarHandler(), params);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

}
