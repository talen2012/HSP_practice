package com.talen.tankgame;

import java.awt.*;

public class MyPanel extends Panel {
    private Hero hero = null;

    MyPanel() {
        hero = new Hero(100, 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // 绘制游戏区域，默认是黑色
        g.fillRect(0, 0, 1000, 750);
        // 绘制Hero坦克，使用封装方法
        drawTank(hero.getX(), hero.getY(), g, 0, 0);
    }

    /**
     * @param x    坦克的左上角x坐标
     * @param y    坦克的左上角y坐标
     * @param g    画笔
     * @param dir  坦克的方向(上下左右)
     * @param type 坦克类型(Hero/敌方), 用于确定颜色
     */
    public void drawTank(int x, int y, Graphics g, int dir, int type) {
        switch (type) {
            case 0: // 我们的坦克
                g.setColor(Color.cyan);
                break;
            case 1: // 敌方的坦克
                g.setColor(Color.yellow);
                break;
        }

        switch (dir) {
            case 0: // 上
                g.fill3DRect(x, y, 10, 60, false); // 左履带
                g.fill3DRect(x + 10, y + 10, 20, 40, false); // 中间矩形
                g.fillOval(x + 10, y + 20, 20, 20); // 中间炮塔
                g.drawLine(x + 20, y + 30, x + 20, y); // 炮管
                g.fill3DRect(x + 30, y, 10, 60, false); // 右履带

                break;
        }
    }
}
