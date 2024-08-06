package com.talen.encap;

public class Encapsulation {
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.setName("Jack 上了井冈山");
        p1.setAge(30);
        p1.setSalary(20000.0);
        System.out.println(p1.info());

        Person p2 = new Person("smith", 300, 150.0);
        System.out.println(p2.info());
    }
}

class Person {
    /*
    不能随便查看人的年龄,工资等隐私，并对设置的年龄进行合理的验证。年龄合理就设置，否则给默认
    年龄, 必须在 1-120, 年龄， 工资不能直接查看 ， name 的长度在 2-6 字符 之间
     */
    public String name;
    private int age; // 不公开
    private double salary; // 不公开

    // 单纯使用setter进行数据合法性验证是不够的，还需要在构造函数中也调用setter

    public Person() {
    }

//    public Person(String name, int age, double salary) {
//        this.name = name;
//        this.age = age;
//        this.salary = salary;
//    }

    public Person(String name, int age, double salary) {
        setName(name);
        setAge(age);
        setSalary(salary);
    }


    // 使用alt+insert快速生成setter和getter代码
    // 在setter中加入数据验证机逻辑

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() >= 2 && name.length() <=6) {
            this.name = name;
        } else {
            System.out.println("输入的名字不对，应是2-6个字符，设为默认：无名");
            this.name = "无名";
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0 && age <= 120) {
            this.age = age;
        } else {
            System.out.println("你设置的年龄不对（1-120岁之间），设置为默认18");
            this.age = 18; // 默认年龄
        }
    }

    public double getSalary() {
        // 可以增加一些权限判断
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // 返回属性信息的方法
    public String info() {
        return "name=" + name + " age=" + age + " salary=" + salary;
    }
}