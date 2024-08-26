package com.talen.tankgame;

import javax.swing.*;

/**
 * 实现坦克的绘制
 */
public class HspTankGame01 extends JFrame {
    private MyPanel mp = null;

    public static void main(String[] args) {
        HspTankGame01 hspTankGame01 = new HspTankGame01();
    }

    HspTankGame01() {
        mp = new MyPanel();
        this.add(mp); // 窗口添加面板(面板包含绘制出的内容)
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
