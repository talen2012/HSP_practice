package com.talen.poly_.polyarr;

public class Student extends Person{
    private double score;

    public Student(String name, int age, double score) {
        super(name, age);
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String say() {
        return "学生 " + super.say() + "\t" + score;
    }

    public void study() {
        System.out.println("学生 " + getName() + "\t正在学Java...");
    }
}

