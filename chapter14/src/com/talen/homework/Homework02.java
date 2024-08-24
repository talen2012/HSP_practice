package com.talen.homework;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Homework02 {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        ArrayList carList = new ArrayList();
        carList.add(new Car("轩逸", 100000));
        carList.add(new Car("卡罗拉", 120000));
        carList.add(new Car("别克", 130000));
        carList.add(new Car("奇瑞", 50000));
        System.out.println("carList为空= " + carList.isEmpty());
        System.out.println("=====增强for遍历");
        for (Object o : carList) {
            System.out.println(o);
        }
        System.out.println("=====迭代器遍历");
        Iterator iterator = carList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

//        carList.clear();
//        System.out.println("======clear()后是否为空= " + carList.isEmpty());
        HashSet carSet = new HashSet();
        carSet.add(new Car("轩逸", 100000));
        carSet.add(new Car("奇瑞", 50000));

        carList.addAll(carSet);
        System.out.println("======addAll(carSet)之后");
        for (Object o : carList) {
            System.out.println(o);
        }

        System.out.println("containsAll(carSet) = " + carList.containsAll(carSet));
//        carList.remove(new Car("奇瑞", 50000));
        carList.removeAll(carSet);
        System.out.println("======removeAll(carSet)");
        for (Object o : carList) {
            System.out.println(o);
        }
    }
}

class Car {
    private String name;
    private double price;

    public Car(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(price, car.price) == 0 && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}