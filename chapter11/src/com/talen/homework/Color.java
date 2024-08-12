package com.talen.homework;

public enum Color implements IA{
    RED(255, 0, 0), BLUE(0, 0, 255), BLACK(0, 0, 0),
    YELLOW(255, 255, 0), GREEN(0, 255, 0),
    ;
    private int redVal;
    private int greenVal;
    private int blueVal;

    private Color(int redVal, int greenVal, int blueVal) {
        this.redVal = redVal;
        this.greenVal = greenVal;
        this.blueVal = blueVal;
    }

    @Override
    public void show() {
        System.out.println("红：" + redVal + " 绿：" + greenVal + " 蓝：" + blueVal);
    }
}
