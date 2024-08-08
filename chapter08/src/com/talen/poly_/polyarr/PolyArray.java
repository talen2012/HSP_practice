package com.talen.poly_.polyarr;

public class PolyArray {
    public static void main(String[] args) {
        // 1个Person，2个Student, 2个Teacher，统一放在一个数组里，并调用每个对象的say方法
        Person[] persons = new Person[5];
        persons[0] = new Person("jack", 20);
        persons[1] = new Student("jack", 18, 100);
        persons[2] = new Student("smith", 19, 30.1);
        persons[3] = new Teacher("scott", 30, 20000);
        persons[4] = new Teacher("king", 50, 25000);

        for (int i = 0; i < persons.length; i++) {
            System.out.println(persons[i].say()); // 动态绑定
            if (persons[i] instanceof Teacher) { // 向下转型
                ((Teacher) persons[i]).teach();
            } else if (persons[i] instanceof Student) {
                ((Student) persons[i]).study();
            }
        }

    }
}
