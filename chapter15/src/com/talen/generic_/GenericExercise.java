package com.talen.generic_;

public class GenericExercise {
    public static void main(String[] args) {

    }
}

interface IA {
    void show1();
    void show2();
}

class A<R> implements IA {

    public <T> void test() {
        R r;
        T t;
    }

    @Override
    public void show1() {
        System.out.println("show1");
    }

    @Override
    public void show2() {
        System.out.println("show2");
    }
}

class B extends A implements IA{

}