package com.talen.homework;

public class Homework05 {
    public static void main(String[] args) {
        A a = new A();
        a.m1();
    }
}

class A {
    private final String NAME = "A";
    public void m1() {
        class B {
            private final String NAME = "B";
            public void show() {
                System.out.println(A.this.NAME);
            }
        }
        B b = new B();
        b.show();
    }
}