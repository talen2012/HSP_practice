package com.talen.tankgame07;

public class EnemyTank extends Tank implements Runnable {
    public EnemyTank(int x, int y, int dir) {
        super(x, y, dir);
    }

    // 由于敌方坦克是自由移动的，要重写上下左右移动方法
    @Override
    public void moveUp() {
        int y_ = getY() - getSpeed();
        // 前进有碰撞，不走
        if (moveCollisionDetection(this.getX(), y_, this.getDir())) {
            return;
        }
        // 碰到墙壁立马随机转向
        if (y_ <= 0) {
            int dir_ = randomDir();
            // 转向有碰撞，放弃
            if (changeDirCollisionDetection(dir_)) {
                return;
            }
            setDir(dir_);
            return;
        }
        setY(y_);
    }

    @Override
    public void moveRight() {
        int x_ = getX() + getSpeed();
        // 前进有碰撞，不走
        if (moveCollisionDetection(x_, this.getY(), this.getDir())) {
            return;
        }
        // 碰到墙壁立马随机转向
        if (x_ >= 1000 - 60) {
            int dir_ = randomDir();
            // 转向有碰撞，放弃
            if (changeDirCollisionDetection(dir_)) {
                return;
            }
            setDir(dir_);
            return;
        }
        setX(x_);
    }

    @Override
    public void moveDown() {
        int y_ = getY() + getSpeed();
        // 前进有碰撞，不走
        if (moveCollisionDetection(this.getX(), y_, this.getDir())) {
            return;
        }
        // 碰到墙壁立马随机转向
        if (y_ >= 750 - 60) {
            int dir_ = randomDir();
            // 转向有碰撞，放弃
            if (changeDirCollisionDetection(dir_)) {
                return;
            }
            setDir(dir_);
            return;
        }
        setY(y_);
    }

    @Override
    public void moveLeft() {
        int x_ = getX() - getSpeed();
        // 前进有碰撞，不走
        if (moveCollisionDetection(x_, this.getY(), this.getDir())) {
            return;
        }
        // 碰到墙壁立马随机转向
        if (x_ <= 0) {
            int dir_ = randomDir();
            // 转向有碰撞，放弃
            if (changeDirCollisionDetection(dir_)) {
                return;
            }
            setDir(dir_);
            return;
        }
        setX(x_);
    }

    public int randomDir() {
        return (int)(Math.random() * 4);
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            // 坦克死亡，就不再发射子弹, 不再自由移动
            if (!isAlive) {
                break;
            }

            // 坦克每50ms沿当前方向移动一次
            switch (getDir()) {
                case 0:
                    moveUp();
                    break;
                case 1:
                    moveRight();
                    break;
                case 2:
                    moveDown();
                    break;
                case 3:
                    moveLeft();
                    break;
            }

            // 每三秒发射子弹
            if (i % 30 == 0) {
                shotEnemy();
            }
            i++;
            // 每五秒坦克随机改变方向(刚开始不改变)
            if (i % 50 == 0) {
                int dir_ = randomDir();
                while(changeDirCollisionDetection(dir_)) {
                    dir_ = randomDir();
                }
                setDir(dir_);
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
