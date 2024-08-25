package com.talen.homework;

import org.junit.jupiter.api.Test;

import java.util.*;

public class Homework01 {
    public static void main(String[] args) {

    }

    @Test
    public void testList() {
        DAO<User> dao = new DAO<User>();
        dao.save("001", new User(1, 10, "jack"));
        dao.save("002", new User(2, 15, "king"));
        dao.save("003", new User(3, 7, "smith"));

        List<User> list = dao.list();
        System.out.println("list=" + list);

        dao.update("003", new User(3, 58, "milan"));
        list = dao.list();
        System.out.println("===修改后list=" + list);

        dao.delete("001");
        list = dao.list();
        System.out.println("===删除“001”后list=" + list);

        System.out.println("id=003 " + dao.get("003"));
    }
}

class DAO<T> {
    private Map<String, T> map;

    public DAO() {
        map = new HashMap<String, T>();
    }

    public void save(String id, T entity) {
        map.put(id, entity);
    }

    public T get(String id) {
        return map.get(id);
    }

    public void update(String id, T entity) {
        map.put(id, entity);
    }

    public List<T> list() {
        // return map.values(); // 错误，map.values返回collection<T>
        List<T> entityList = new ArrayList<>();
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            entityList.add(map.get(key));
        }
        return entityList;
    }

    public void delete(String id) {
        map.remove(id);
    }
}

class User {
    private int id;
    private int age;
    private String name;

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}