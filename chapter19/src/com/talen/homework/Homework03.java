package com.talen.homework;

import java.io.*;
import java.util.Properties;

public class Homework03 {
    /**
     * 从Dog.properties中读取属性
     * 并初始化一个Dog对象
     * @param args
     */
    public static void main(String[] args) {
        Properties dogProperties = new Properties();
        try {
            dogProperties.load(new FileInputStream("e:/Java_practice/HSP_practice/chapter19/Dog.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String name = dogProperties.getProperty("name");
        String age = dogProperties.getProperty("age");
        String color = dogProperties.getProperty("color");

        Dog dog = new Dog(name, Integer.parseInt(age), color);
        System.out.println(dog);

        // 将创建的文件序列化到文件dog.data中
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("e:/Java_practice/HSP_practice/chapter19/dog.data"));
            oos.writeObject(dog);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // 从dog.data中读取dog
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("e:/Java_practice/HSP_practice/chapter19/dog.data"));
            Object dog2 = ois.readObject();
            System.out.println(dog2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Dog implements Serializable {
    private String name;
    private int age;
    private String color;

    public Dog(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }
}