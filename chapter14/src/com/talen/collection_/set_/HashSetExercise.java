package com.talen.collection_.set_;

import java.util.HashSet;
import java.util.Objects;

public class HashSetExercise {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
        hashSet.add(new Employee("smith", 18));
        hashSet.add(new Employee("jack", 23));
        hashSet.add(new Employee("smith", 18));
        System.out.println("hashSet = " + hashSet);
    }
}

class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 默认的hashCode()方法，如果对象地址不同，那么hash值也是不同的
    // 这里需要重写，让虽然不是同一个对象，但内容相同，也能产生相同hash值


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
