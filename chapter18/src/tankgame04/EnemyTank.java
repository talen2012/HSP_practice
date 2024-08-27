package tankgame04;

public class EnemyTank extends Tank implements Runnable {
    public EnemyTank(int x, int y, int dir) {
        super(x, y, dir);
    }

    // 由于敌方坦克是自由移动的，要重写上下左右移动方法
    @Override
    public void moveUp() {
        // 碰到墙壁立马随机转向
        int y = getY() - getSpeed();
        if (y <= 0) {
            randomDir();
        } else {
            setY(y);
        }
    }

    @Override
    public void moveRight() {
        // 碰到墙壁立马随机转向
        int x = getX() + getSpeed();
        if (x >= 1000 - 60) {
            randomDir();
        } else {
            setX(x);
        }
    }

    @Override
    public void moveDown() {
        // 碰到墙壁立马随机转向
        int y = getY() + getSpeed();
        if (y >= 750 - 60) {
            randomDir();
        } else {
            setY(y);
        }
    }

    @Override
    public void moveLeft() {
        // 碰到墙壁立马随机转向
        int x = getX() - getSpeed();
        if (x <= 0) {
            randomDir();
        } else {
            setX(x);
        }
    }

    public void randomDir() {
        setDir((int)(Math.random() * 4));
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
            // 每五秒坦克随机改变方向
            if (i % 50 == 0) {
                randomDir();
            }


            // 每三秒发射子弹
            if (i % 30 == 0) {
                shotEnemy();
            }

            i++;

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
