package tankgame04;

import java.util.Vector;

public class Tank {
    // x，y是Tank的初始坐标
    private int x;
    private int y;
    private int dir; // 方向 0-上 1-右 2-下 3-左
    private int speed = 2;
    Boolean isAlive = true; // 坦克是否存活
    Vector<Shot> shots = new Vector<>();

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
        y = Math.max(y - speed, 0);
    }

    public void moveRight() {
        dir = 1;
        x = Math.min(x + speed, 1000 - 60);
    }

    public void moveDown() {
        dir = 2;
        y = Math.min(y + speed, 750 - 60);
    }

    public void moveLeft() {
        dir = 3;
        x = Math.max(x - speed, 0);
    }

    public void shotEnemy() {
        // 限制存活的子弹最多5颗
        if (shots.size() >= 5) {
            return;
        }
        Shot shot = null;
        switch (getDir()) {
            case 0: // 上
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1: // 右
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2: // 下
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3: // 左
                shot = new Shot(getX(), getY() + 20, 3);
                break;
        }
        shots.add(shot);
        new Thread(shot).start(); // 启动发射子弹线程
    }
}
