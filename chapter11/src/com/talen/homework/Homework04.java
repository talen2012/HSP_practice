package com.talen.homework;

public class Homework04 {
    public static void main(String[] args) {
        Cellphone cellphone = new Cellphone();
        cellphone.testWork(new Cal() {
            @Override
            public void work() {
                System.out.println("计算");
            }
        });
    }
}

interface Cal {
    void work(); // 默认是abstract public的
}

class Cellphone {
    public void testWork(Cal calculator) {
        calculator.work();
    }
}