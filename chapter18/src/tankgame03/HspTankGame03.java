package tankgame03;

import javax.swing.*;

/**
 * 1. 实现上下左右方向坦克的绘制
 * 2. 实现键盘awsd键控制坦克移动
 * 3. 实现按下键盘j键发射子弹
 */
public class HspTankGame03 extends JFrame {
    private MyPanel mp = null;

    public static void main(String[] args) {
        HspTankGame03 hspTankGame03 = new HspTankGame03();
    }

    HspTankGame03() {
        mp = new MyPanel();
        // 将mp放入Thread中，并启动
        Thread repaintThread = new Thread(mp);
        repaintThread.start();

        this.add(mp); // 窗口添加面板(面板包含绘制出的内容)
        this.addKeyListener(mp); // 添加键盘监听器

        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
