package com.talen.homework;

public class Homework03 {
    public static void main(String[] args) {
        String name = "Han Shun Ping";
        printName(null);
    }

    public static void printName(String name) {
        if (name == null) {
            System.out.println("名字为null");
            return;
        }
        String[] subNames = name.split(" ");
        if (subNames.length != 3) {
            System.out.println("名字格式有误");
        }
        System.out.printf("%s, %s .%c%n", subNames[2], subNames[0], subNames[1].charAt(0)); // printf()方法功能是格式化输出，%s表示字符串，%c表示字符
    }
}
