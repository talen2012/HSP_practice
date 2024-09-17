package com.talen.dao_.test;

import com.talen.dao_.dao.ActorDAO;
import com.talen.dao_.domain.Actor;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author 田磊
 * @version 1.0
 * @date 2024/9/16
 * @time 15:47
 */
public class TestDAO {
    private ActorDAO actorDAO =  new ActorDAO();

    @Test
    public void testActorDAO() {
        // 测试DML
        int rows =
        actorDAO.update("insert into actor values (null, ?, ?, ?, ?)", "胡歌", "男", "1997-01-08", "1278");
        // 测试查询多条记录
        System.out.println(rows > 0 ? "执行成功" : "执行没有影响到表");
        List<Actor> list =
                actorDAO.queryMulti("select * from actor where id >= ?", Actor.class, 1);
        System.out.println("========查询全部记录的结果");
        for (Actor actor : list) {
            System.out.println(actor);
        }

        // 测试查询单条记录
        Actor actor = actorDAO.querySingle("select * from actor where id = ?", Actor.class, 3);
        System.out.println("========查询单条记录的结果");
        System.out.println(actor);

        // 测试查询单条单列
        Object name =
        actorDAO.queryScalar("select name from actor where id = ?", 1);
        System.out.println("=======查询单条单列的结果");
        System.out.println(name);

    }
}
