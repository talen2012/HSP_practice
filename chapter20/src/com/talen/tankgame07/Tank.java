package com.talen.tankgame07;

import java.util.Vector;

public class Tank {
    // x，y是Tank的初始坐标
    private int x;
    private int y;
    private int dir; // 方向 0-上 1-右 2-下 3-左
    private int speed = 2;
    Boolean isAlive = true; // 坦克是否存活
    Vector<Shot> shots = new Vector<>();
    Vector<Tank> tanksCollection = new Vector<>(); // 屏幕上所有坦克的集合，用于碰撞检测

    public Tank(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    /**
     * 创建对象后，要立即调用传入屏幕上的坦克集合
     * @param tanksCollection
     */
    public void setTanksCollection(Vector<Tank> tanksCollection) {
        this.tanksCollection = tanksCollection;
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
        int y_ = y - speed;
        if (moveCollisionDetection(this.x, y_, this.dir) || y_ < 0) {
            return;
        }
        y = y_;
    }

    public void moveRight() {
        int x_ = x + speed;
        if (moveCollisionDetection(x_, this.y, this.dir) || x_ > 1000 - 60) {
            return;
        }
        x = x_;
    }

    public void moveDown() {
        int y_ = y + speed;
        if (moveCollisionDetection(this.x, y_, this.dir) || y_ > 750 - 60) {
            return;
        }
        y = y_;
    }

    public void moveLeft() {
        int x_ = x - speed;
        if (moveCollisionDetection(x_, this.y, this.dir) || x_ < 0) {
            return;
        }
        x = x_;
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

    /**
     * 移动碰撞检测
     * 1. 坦克是个长方形，处于左右，和上下时覆盖区域是不同的，分开讨论
     * 2. 本坦克与其它坦克进行碰撞检测
     * 3. 在坦克移动时调用此函数
     * 4. 考虑移动速度，检测的是移动后是否碰撞
     *    需要传入移动后的坐标和方向，因此这里的x, y，dir并不直接是本坦克的
     */
    // 移动碰撞检测
    // 1. 坦克是个长方形，处于左右，和上下时覆盖区域是不同的，分开讨论
    // 2. 本坦克与其它坦克进行碰撞检测
    // 3. 在坦克移动时调用此函数
    // 4. 考虑移动速度，检测的是移动后是否碰撞
    //    需要传入移动和转向后的坐标和方向，因此这里的x, y，dir并不直接是本坦克的
    public Boolean moveCollisionDetection(int x_, int y_, int dir_) {
        switch (dir_) {
            case 0: // 移动后向上
                // 检查移动后左、右上角是否处于其它坦克区域
                for (Tank singleTank : tanksCollection) {
                    if (singleTank == this || !singleTank.isAlive) {
                        continue;
                    }
                    // 1. 其它坦克处于上下方向
                    //    x坐标范围[singleTank.x, singleTank.x + 40]
                    //    y坐标范围[singleTank.y, singleTank.y + 60]
                    if (singleTank.dir == 0 || singleTank.dir == 2) {
                        // 1.1 移动后左上角坐标[x_, y_]
                        if (x_ >= singleTank.x
                                && x_ <= singleTank.x + 40
                                && y_ >= singleTank.y
                                && y_ <= singleTank.y + 60) {
                            return true;
                        }
                        // 1.2 移动后右上角坐标[x_ + 40, y_]
                        if (x_ + 40 >= singleTank.x
                                && x_ + 40 <= singleTank.x + 40
                                && y_ >= singleTank.y
                                && y_ <= singleTank.y + 60) {
                            return true;
                        }
                    }
                    // 2. 其它坦克处于右左方向
                    //    x坐标范围[singleTank.x, singleTank.x + 60]
                    //    y坐标范围[singleTank.y, singleTank.y + 40]
                    if (singleTank.dir == 1 || singleTank.dir == 3) {
                        // 2.1 移动后左上角坐标[x_, y_]
                        if (x_ >= singleTank.x
                                && x_ <= singleTank.x + 60
                                && y_ >= singleTank.y
                                && y_ <= singleTank.y + 40) {
                            return true;
                        }
                        // 2.2 移动后右上角坐标[x_ + 40, y_]
                        if (x_ + 40 >= singleTank.x
                                && x_ + 40 <= singleTank.x + 60
                                && y_ >= singleTank.y
                                && y_ <= singleTank.y + 40) {
                            return true;
                        }
                    }
                }
                break;
            case 1: // 移动后向右
                // 检查移动后右下角、右上角是否处于其它坦克区域
                for (Tank singleTank : tanksCollection) {
                    if (singleTank == this || !singleTank.isAlive) {
                        continue;
                    }
                    // 1. 其它坦克处于上下方向
                    //    x坐标范围[singleTank.x, singleTank.x + 40]
                    //    y坐标范围[singleTank.y, singleTank.y + 60]
                    if (singleTank.dir == 0 || singleTank.dir == 2) {
                        // 1.1 移动后右下角坐标[x_ + 60, y_ + 40]
                        if (x_ + 60 >= singleTank.x
                                && x_ + 60 <= singleTank.x + 40
                                && y_ + 40 >= singleTank.y
                                && y_ + 40 <= singleTank.y + 60) {
                            return true;
                        }
                        // 1.2 移动后右上角坐标[x_ + 60, y_]
                        if (x_ + 60 >= singleTank.x
                                && x_ + 60 <= singleTank.x + 40
                                && y_ >= singleTank.y
                                && y_ <= singleTank.y + 60) {
                            return true;
                        }
                    }
                    // 2. 其它坦克处于右左方向
                    //    x坐标范围[singleTank.x, singleTank.x + 60]
                    //    y坐标范围[singleTank.y, singleTank.y + 40]
                    if (singleTank.dir == 1 || singleTank.dir == 3) {
                        // 2.1 移动后右下角坐标[x_ + 60, y_ + 40]
                        if (x_ + 60 >= singleTank.x
                                && x_ + 60 <= singleTank.x + 60
                                && y_ + 40 >= singleTank.y
                                && y_ + 40 <= singleTank.y + 40) {
                            return true;
                        }
                        // 2.2 移动后右上角坐标[x_ + 60, y_]
                        if (x_ + 60 >= singleTank.x
                                && x_ + 60 <= singleTank.x + 60
                                && y_ >= singleTank.y
                                && y_ <= singleTank.y + 40) {
                            return true;
                        }
                    }
                }
                break;
            case 2: // 移动后向下
                // 检查移动后左、右下角是否处于其它坦克区域
                for (Tank singleTank : tanksCollection) {
                    if (singleTank == this || !singleTank.isAlive) {
                        continue;
                    }
                    // 1. 其它坦克处于上下方向
                    //    x坐标范围[singleTank.x, singleTank.x + 40]
                    //    y坐标范围[singleTank.y, singleTank.y + 60]
                    if (singleTank.dir == 0 || singleTank.dir == 2) {
                        // 1.1 移动后左下角坐标[x_, y_ + 60]
                        if (x_ >= singleTank.x
                                && x_ <= singleTank.x + 40
                                && y_ + 60 >= singleTank.y
                                && y_ + 60 <= singleTank.y + 60) {
                            return true;
                        }
                        // 1.2 移动后右下角坐标[x_ + 40, y_ + 60]
                        if (x_ + 40 >= singleTank.x
                                && x_ + 40 <= singleTank.x + 40
                                && y_ + 60 >= singleTank.y
                                && y_ + 60 <= singleTank.y + 60) {
                            return true;
                        }
                    }
                    // 2. 其它坦克处于右左方向
                    //    x坐标范围[singleTank.x, singleTank.x + 60]
                    //    y坐标范围[singleTank.y, singleTank.y + 40]
                    if (singleTank.dir == 1 || singleTank.dir == 3) {
                        // 2.1 移动后左下角坐标[x_, y_ + 60]
                        if (x_ >= singleTank.x
                                && x_ <= singleTank.x + 60
                                && y_ + 60 >= singleTank.y
                                && y_ + 60 <= singleTank.y + 40) {
                            return true;
                        }
                        // 2.2 移动后右下角坐标[x_ + 40, y_ + 60]
                        if (x_ + 40 >= singleTank.x
                                && x_ + 40 <= singleTank.x + 60
                                && y_ + 60 >= singleTank.y
                                && y_ + 60 <= singleTank.y + 40) {
                            return true;
                        }
                    }
                }
                break;
            case 3: // 移动后向左
                // 检查移动后左上角、左下角是否处于其它坦克区域
                for (Tank singleTank : tanksCollection) {
                    if (singleTank == this || !singleTank.isAlive) {
                        continue;
                    }
                    // 1. 其它坦克处于上下方向
                    //    x坐标范围[singleTank.x, singleTank.x + 40]
                    //    y坐标范围[singleTank.y, singleTank.y + 60]
                    if (singleTank.dir == 0 || singleTank.dir == 2) {
                        // 1.1 移动后左上角坐标[x_, y_]
                        if (x_ >= singleTank.x
                                && x_ <= singleTank.x + 40
                                && y_ >= singleTank.y
                                && y_ <= singleTank.y + 60) {
                            return true;
                        }
                        // 1.2 移动后左下角坐标[x_, y_ + 40]
                        if (x_ >= singleTank.x
                                && x_ <= singleTank.x + 40
                                && y_ + 40 >= singleTank.y
                                && y_ + 40 <= singleTank.y + 60) {
                            return true;
                        }
                    }
                    // 2. 其它坦克处于右左方向
                    //    x坐标范围[singleTank.x, singleTank.x + 60]
                    //    y坐标范围[singleTank.y, singleTank.y + 40]
                    if (singleTank.dir == 1 || singleTank.dir == 3) {
                        // 2.1 移动后左上角坐标[x_, y_]
                        if (x_ >= singleTank.x
                                && x_ <= singleTank.x + 60
                                && y_ >= singleTank.y
                                && y_ <= singleTank.y + 40) {
                            return true;
                        }
                        // 2.2 移动后左下角坐标[x_, y_ + 40]
                        if (x_ >= singleTank.x
                                && x_ <= singleTank.x + 60
                                && y_ + 40 >= singleTank.y
                                && y_ + 40 <= singleTank.y + 40) {
                            return true;
                        }
                    }
                }
                break;
        }
        return false;
    }

    /**
     * 转向碰撞检测
     * 1. 坦克是个长方形，处于左右，和上下时覆盖区域是不同的，分开讨论
     * 2. 本坦克与其它坦克进行碰撞检测
     * 3. 在坦克转向时调用此函数
     * 4. 考虑转向，检测的是转向后是否碰撞
     * @param newDir_ 计划转向的方向
     */
    public Boolean changeDirCollisionDetection(int newDir_) {
        // 左右方向转换，上下方向转换，覆盖区域不变，不会碰撞
        // 1. 左右方向转换为上下方向
        if (this.dir == 1 || this.dir == 3) {
            if (newDir_ == 0 || newDir_ == 2) {
                for (Tank singleTank : tanksCollection) {
                    if (singleTank == this || !singleTank.isAlive) {
                        continue;
                    }
                    // 1. 其它坦克处于上下方向
                    //    x坐标范围[singleTank.x, singleTank.x + 40]
                    //    y坐标范围[singleTank.y, singleTank.y + 60]
                    if (singleTank.dir == 0 || singleTank.dir == 2) {
                        // 1.1 转向后的左下角坐标[this.x, this.y + 60]
                        if (this.x >= singleTank.x
                                && this.x <= singleTank.x + 40
                                && this.y + 60 >= singleTank.y
                                && this.y + 60 <= singleTank.y + 60) {
                            return true;
                        }
                        // 1.2 转向后的右下角坐标[this.x + 40, this.y + 60]
                        if (this.x + 40 >= singleTank.x
                                && this.x + 40 <= singleTank.x + 40
                                && this.y + 60 >= singleTank.y
                                && this.y + 60 <= singleTank.y + 60) {
                            return true;
                        }
                    }
                    // 2. 其它坦克处于右左方向
                    //    x坐标范围[singleTank.x, singleTank.x + 60]
                    //    y坐标范围[singleTank.y, singleTank.y + 40]
                    if (singleTank.dir == 1 || singleTank.dir == 3) {
                        // 2.1 转向后的左下角坐标[this.x, this.y + 60]
                        if (this.x >= singleTank.x
                                && this.x <= singleTank.x + 60
                                && this.y + 60 >= singleTank.y
                                && this.y + 60 <= singleTank.y + 40) {
                            return true;
                        }
                        // 1.2 转向后的右下角坐标[this.x + 40, this.y + 60]
                        if (this.x + 40 >= singleTank.x
                                && this.x + 40 <= singleTank.x + 60
                                && this.y + 60 >= singleTank.y
                                && this.y + 60 <= singleTank.y + 40) {
                            return true;
                        }
                    }
                }



            }
        }
        // 二. 上下方向转换为左右方向
        if (this.dir == 0 || this.dir == 2) {
            if (newDir_ == 1 || newDir_ == 3) {
                for (Tank singleTank : tanksCollection) {
                    if (singleTank == this || !singleTank.isAlive) {
                        continue;
                    }
                    // 1. 其它坦克处于上下方向
                    //    x坐标范围[singleTank.x, singleTank.x + 40]
                    //    y坐标范围[singleTank.y, singleTank.y + 60]
                    if (singleTank.dir == 0 || singleTank.dir == 2) {
                        // 1.1 转向后的右上角坐标[this.x + 60, this.y]
                        if (this.x + 60 >= singleTank.x
                                && this.x + 60 <= singleTank.x + 40
                                && this.y >= singleTank.y
                                && this.y <= singleTank.y + 60) {
                            return true;
                        }
                        // 1.2 转向后的右下角坐标[this.x + 60, this.y + 40]
                        if (this.x + 60 >= singleTank.x
                                && this.x + 60 <= singleTank.x + 40
                                && this.y + 40 >= singleTank.y
                                && this.y + 40 <= singleTank.y + 60) {
                            return true;
                        }
                    }
                    // 2. 其它坦克处于右左方向
                    //    x坐标范围[singleTank.x, singleTank.x + 60]
                    //    y坐标范围[singleTank.y, singleTank.y + 40]
                    if (singleTank.dir == 1 || singleTank.dir == 3) {
                        // 2.1 转向后的右上角坐标[this.x + 60, this.y]
                        if (this.x + 60 >= singleTank.x
                                && this.x + 60 <= singleTank.x + 60
                                && this.y >= singleTank.y
                                && this.y <= singleTank.y + 40) {
                            return true;
                        }
                        // 2.2 转向后的右下角坐标[this.x + 60, this.y + 40]
                        if (this.x + 60 >= singleTank.x
                                && this.x + 60 <= singleTank.x + 60
                                && this.y + 40 >= singleTank.y
                                && this.y + 40 <= singleTank.y + 40) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
}
