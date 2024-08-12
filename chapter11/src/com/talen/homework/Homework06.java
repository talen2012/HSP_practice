package com.talen.homework;

public class Homework06 {
    public static void main(String[] args) {
        Person jack = new Person("jack", new Horse());
        jack.common();
        jack.passRiver();
    }
}

interface Vehicles {
    void work();
}

class Horse implements Vehicles {
    @Override
    public void work() {
        System.out.println("小马在奔跑");
    }
}

class Boat implements Vehicles {
    @Override
    public void work() {
        System.out.println("船在水上前行");
    }
}



class Person {
    private String name;
    private Vehicles vehicles;

    public Person(String name, Vehicles vehicles) {
        this.name = name;
        this.vehicles = vehicles;
    }

    public void passRiver() {
        if (!(vehicles instanceof Boat)) {
            vehicles = VehicleFactory.getBoat();
        }
        vehicles.work();
    }

    public void common() {
        if (!(vehicles instanceof Horse)) { // vehicle为空，返回的也是假
            vehicles = VehicleFactory.getHorse();
        }
        vehicles.work();
    }
}

class VehicleFactory {
    private static Horse horse = new Horse();

    private VehicleFactory() {};
    public static Horse getHorse() {
//        return new Horse();
        // 假设马在人的旅途中一直都是同一个，这里用单例的饿汉模式
        return horse;
    }

    public static Boat getBoat() {
        return new Boat();
    }
}
