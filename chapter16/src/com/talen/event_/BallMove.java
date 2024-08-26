package com.talen.event_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 小球通过键盘控制上下左右移动
 */
public class BallMove extends JFrame {
    private MyPanel mp = null;
    public static void main(String[] args) {
        new BallMove();
    }

    BallMove() {
        mp = new MyPanel();
        this.add(mp);
        this.addKeyListener(mp); // JFrame对象可以监听键盘事件，传入键盘监听处理的接口KeyListener
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

// 面板，画出小球
// 实现KeyListener接口，可以监听键盘事件
class MyPanel extends JPanel implements KeyListener {
    // 为了使小球可以移动，左上角坐标设为变量
    private int x = 10;
    private int y = 10;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x, y, 20, 20);
    }

    // 有字符输入，方法触发
    @Override
    public void keyTyped(KeyEvent e) {}

    // 有键被按下，触发方法
    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println((char)(e.getKeyCode()) + "被按下...");
        // 根据用户按下的方向键控制小球移动
        // Java中为每一个键, 分配一个值int(实际上就是ASCII码)
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            y++;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            y--;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            x--;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            x++;
        }
        this.repaint();
    }

    // 有键被释放，触发方法
    @Override
    public void keyReleased(KeyEvent e) {}
}
