package com.talen.tankgame07;

/**
 * 实现子弹设计，按照指定速度和方向改变子弹坐标
 */
public class Shot implements Runnable{
    int x; // 子弹x坐标
    int y; // 子弹y坐标
    int dir; // 子弹方向
    int speed = 8; // 子弹移动速度
    Boolean isAlive = true; // 子弹是否存活

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Shot(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            switch (dir) {
                case 0: // 上
                    y -= speed;
                    break;
                case 1: // 右
                    x += speed;
                    break;
                case 2: // 下
                    y += speed;
                    break;
                case 3: // 左
                    x -= speed;
                    break;
            }
            // System.out.println("子弹坐标 " + x + " " + y);
            // 子弹碰到边缘，进程退出
            // 在MyPanel中使用hitTank()方法检测，子弹打到敌人，将isAlive置为false, 进程退出
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isAlive)) {
                // System.out.println("子弹消失...");
                isAlive = false;
                break;
            }
        }
    }
}
