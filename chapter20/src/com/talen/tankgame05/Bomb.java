package com.talen.tankgame05;

public class Bomb {
    int x; // 爆炸效果左上角x坐标
    int y;
    int life = 9;
    Boolean isAlive = true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void lifeDown() {
        if (life > 0) {
            life--;
        } else {
            isAlive = false;
        }
    }
}
