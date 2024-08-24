package com.talen.collection_.set_;

import java.util.HashSet;
import java.util.Objects;

public class HashSetExercise02 {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
        hashSet.add(new Employee02("smith", 3000.0, new MyDate(2001, 1, 2)));
        hashSet.add(new Employee02("jack", 5000.0, new MyDate(2002, 3, 4)));
        hashSet.add(new Employee02("smith", 3500.0, new MyDate(2001, 1, 2)));

        System.out.println("hashSet = " + hashSet);
    }
}

class Employee02 {
    private String name;
    private double sal;
    private MyDate birthDay;

    public Employee02(String name, double sal, MyDate birthDay) {
        this.name = name;
        this.sal = sal;
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee02 that = (Employee02) o;
        return Objects.equals(name, that.name) && Objects.equals(birthDay, that.birthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthDay);
    }

    @Override
    public String toString() {
        return "Employee02{" +
                "name='" + name + '\'' +
                ", sal=" + sal +
                ", birthDay=" + birthDay.getYear() + "年" + birthDay.getMonth() + "月" + birthDay.getDay() + "日" +
                '}';
    }
}


class MyDate {
    private int year;
    private int month;
    private int day;

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyDate myDate = (MyDate) o;
        return year == myDate.year && month == myDate.month && day == myDate.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }

}