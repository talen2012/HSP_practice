package com.talen;

/**
 * @author 田磊
 * @version 1.0
 * @date 2024/9/1
 * @time 8:15
 */
public class Cat {
    private String name = "果汁";
    protected String color = "白";
    int age = 2;
    public String gender = "male";

    public Cat() {
    }

    public Cat(String name) {
        this.name = name;
    }

    public void hi() {
//        System.out.println("hi");
    }

    public void cry() {
        System.out.println("小猫喵喵叫...");
    }
}

