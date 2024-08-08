package com.talen.override_;

public class OverrideExercise {
    public static void main(String[] args) {
        Person person = new Person("jack", 10);
        System.out.println(person.say());
        Student smith = new Student("smith", 15, "31322355057", 100.0);
        System.out.println(smith.say());
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        setName(name);
        setAge(age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String say() {
        return "名叫" + name + ", 年龄" + age + "岁";
    }
}

class Student extends Person{
    private String  id;
    private double score;

    public Student(String name, int age, String id, double score) {
        super(name, age);
        this.id = id;
        this.score = score;
    }

    public String say() {
        return super.say() + ", id是" + id + ", 分数是" + score;
    }
}