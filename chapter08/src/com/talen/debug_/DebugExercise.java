package com.talen.debug_;

public class DebugExercise {
    public static void main(String[] args) {
        // 创建对象的流程
        // 加载类信息
        // 初始化：默认初始化->显式初始化->构造器初始化
        // 返回对象的地址
        Person jack = new Person("jack", 20);
        System.out.println(jack);
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}