package com.talen.homework;

public class Car {
    private double temperature;

    public Car(double temp) {
        this.temperature = temp;
    }

    class Air {
        public void flow() {
            if (temperature < 0) {
                System.out.println("吹暖风");
            } else if (temperature < 40) {
                System.out.println("关闭空调");
            } else {
                System.out.println("吹冷气");
            }
        }
    }

    public Air getAir() {
        return new Air();
    }
}
