package com.talen.tankgame02;

import javax.swing.*;

/**
 * 1. 实现上下左右方向坦克的绘制
 * 2. 实现键盘awsd键控制坦克移动
 */
public class HspTankGame02 extends JFrame {
    private MyPanel mp = null;

    public static void main(String[] args) {
        HspTankGame02 hspTankGame02 = new HspTankGame02();
    }

    HspTankGame02() {
        mp = new MyPanel();
        this.add(mp); // 窗口添加面板(面板包含绘制出的内容)
        this.addKeyListener(mp); // 添加键盘监听器
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
