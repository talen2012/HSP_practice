package com.talen.tankgame02;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends Panel implements KeyListener {
    private Hero hero = null;
    private Vector<EnemyTank> enemyTanks = new Vector<>();
    private int enemyTankNum = 3;

    MyPanel() {
        // 创建己方坦克
        hero = new Hero(100, 100, 0);
        // 创建敌人坦克
        for (int i = 0; i < enemyTankNum; i++) {
            enemyTanks.add(new EnemyTank(100 * (i + 1), 0, 2));
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // 绘制游戏区域，默认是黑色
        g.fillRect(0, 0, 1000, 750);
        // 绘制Hero坦克，使用封装方法
        drawTank(hero.getX(), hero.getY(), g, hero.getDir(), 1);
        // 绘制敌方坦克
        for (EnemyTank enemy : enemyTanks) { // 注意enemyTanks中敌方坦克被击毁数量会减少，是动态的
            drawTank(enemy.getX(), enemy.getY(), g, enemy.getDir(), 0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * 监听键盘awsd键，控制坦克左上右下移动
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) { // 按下w键，向上
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D) { // 按下d键，向右
            hero.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S) { // 按下s键，向下
           hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) { // 按下a键，向左
            hero.moveLeft();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

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
            case 0: // 敌方的坦克
                g.setColor(Color.cyan);
                break;
            case 1: // 我们的坦克
                g.setColor(Color.yellow);
                break;
        }

        switch (dir) {
            case 0: // 上
                g.fill3DRect(x, y, 10, 60, false); // 左履带
                g.fill3DRect(x + 10, y + 10, 20, 40, false); // 中间矩形
                g.fill3DRect(x + 30, y, 10, 60, false); // 右履带
                g.fillOval(x + 10, y + 20, 20, 20); // 中间炮塔
                g.drawLine(x + 20, y + 30, x + 20, y); // 炮管
                break;
            case 1: // 右
                g.fill3DRect(x, y, 60, 10, false); // 上履带
                g.fill3DRect(x + 10, y + 10, 40, 20, false); // 中间矩形
                g.fill3DRect(x, y + 30, 60, 10, false); // 下履带
                g.fillOval(x + 20, y + 10, 20, 20); // 中间炮塔
                g.drawLine(x + 30, y + 20, x + 60, y + 20); // 炮管
                break;
            case 2: // 下
                g.fill3DRect(x, y, 10, 60, false); // 左履带
                g.fill3DRect(x + 10, y + 10, 20, 40, false); // 中间矩形
                g.fill3DRect(x + 30, y, 10, 60, false); // 右履带
                g.fillOval(x + 10, y + 20, 20, 20); // 中间炮塔
                g.drawLine(x + 20, y + 30, x + 20, y + 60); // 炮管
                break;
            case 3: // 左
                g.fill3DRect(x, y, 60, 10, false); // 上履带
                g.fill3DRect(x + 10, y + 10, 40, 20, false); // 中间矩形
                g.fill3DRect(x, y + 30, 60, 10, false); // 下履带
                g.fillOval(x + 20, y + 10, 20, 20); // 中间炮塔
                g.drawLine(x + 30, y + 20, x, y + 20); // 炮管
                break;
            default:
                System.out.println("坦克方向值不在0-3之间");
        }
    }
}
