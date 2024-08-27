package tankgame03;

import java.util.Vector;

public class Hero extends Tank {
    public Hero(int x, int y, int dir) {
        super(x, y, dir);
    }
    Vector<Shot> shots = new Vector<>();

    public void shotEnemy() {
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
