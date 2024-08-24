package com.talen.map_.hashmap_;

import java.util.*;

public class HashMapExercise {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        Map hashMap = new Properties();
        Empoloyee jack = new Empoloyee("jack", 1, 10000);
        Empoloyee smith = new Empoloyee("smith", 2, 20000);
        Empoloyee lilei = new Empoloyee("lilei", 3, 30000);
        hashMap.put(jack.getId(), jack);
        hashMap.put(smith.getId(), smith);
        hashMap.put(lilei.getId(), lilei);

        // 第一种遍历： 获取所有Key
        System.out.println("======1.1");
        Set keySet = hashMap.keySet();
        for (Object key : keySet) {
            Empoloyee value = (Empoloyee) hashMap.get(key);
            if (value.getId() > 18000) {
                System.out.println(value);
            }
        }
        System.out.println("======1.2");
        Iterator iterator = keySet.iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            Empoloyee value = (Empoloyee) hashMap.get(key);
            if (value.getSal() > 18000) {
                System.out.println(value);
            }
        }

        // 第二遍历，通过entrySet()方法获得键值对
        Set entrySet = hashMap.entrySet();
        System.out.println("======2.1");
        for (Object kv : entrySet) {
            Map.Entry entry = (Map.Entry) kv;
            Empoloyee value = (Empoloyee) entry.getValue();
            if (value.getSal() > 18000) {
                System.out.println(value);
            }
        }

        System.out.println("======2.2");
        Iterator iterator1 = entrySet.iterator();
        while (iterator1.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator1.next();
            Empoloyee value = (Empoloyee) entry.getValue();
            if (value.getSal() > 18000) {
                System.out.println(value);
            }
        }
    }
}

class Empoloyee {
    private String name;
    private int id;
    private double sal;

    public Empoloyee(String name, int id, double sal) {
        this.name = name;
        this.id = id;
        this.sal = sal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    @Override
    public String toString() {
        return "Empoloyee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", sal=" + sal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empoloyee empoloyee = (Empoloyee) o;
        return id == empoloyee.id && Double.compare(sal, empoloyee.sal) == 0 && Objects.equals(name, empoloyee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, sal);
    }
}