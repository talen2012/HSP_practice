package com.talen.tankgame02;

public class Tank {
    // x，y是Tank的初始坐标
    private int x;
    private int y;
    private int dir; // 方向 0-上 1-右 2-下 3-左
    private int speed = 1;

    public Tank(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void moveUp() {
        dir = 0;
        y -= speed;
    }

    public void moveRight() {
        dir = 1;
        x += speed;
    }

    public void moveDown() {
        dir = 2;
        y += speed;
    }

    public void moveLeft() {
        dir = 3;
        x -= speed;
    }
}
