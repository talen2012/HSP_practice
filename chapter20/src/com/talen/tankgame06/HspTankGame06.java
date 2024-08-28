package com.talen.tankgame06;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * 1. 实现上下左右方向坦克的绘制
 * 2. 实现键盘awsd键控制坦克移动
 * 3. 实现按下键盘j键发射子弹
 * 4. 实现了双缓冲绘图，解决了闪烁问题
 * 5. 实现了击中敌人坦克，敌人坦克消失，己方子弹也消失
 * 6. 实现了击中坦克，爆炸效果
 * 7. 实现了敌方坦克每5s改变方向，每3秒发射一颗子弹，每50ms移动2
 * 8. 实现了限制同一坦克，最多只能有五颗子弹存活
 * 9. 实现了敌方坦克击中我方，我方消失，爆炸效果
 * 10. 我方坦克子弹全部消亡后，重生
 * 11. 解决了坦克转向和移动时的重叠问题
 * 12. 实现了记录累计击毁和累计被击毁
 * 13. 实现了记录当前存活坦克状态
 * 14. 实现了恢复上一局游戏的状态
 * 15. 实现了选择继续游戏还是开始新游戏
 * 16. 添加了音乐
 */
public class HspTankGame06 extends JFrame {
    private MyPanel mp = null;
    private Scanner myScanner = new Scanner(System.in);

    public static void main(String[] args) {
        HspTankGame06 hspTankGame06 = new HspTankGame06();
    }

    HspTankGame06() {
        System.out.println("0-开始新游戏，1-继续游戏");
        System.out.print("请选择：");
        int key;
        while (true) {
            try {
                // 不要使用nextInt()方法，抛出了三种异常
                // 类型不匹配就不读取缓冲区了，导致不匹配的内容一直存在缓冲区
                key = Integer.parseInt(myScanner.next());
                if (key == 0 || key == 1) {
                    break;
                } else {
                    System.out.print("输入有误，请输入0 / 1：");
                }
            } catch (NumberFormatException e) {
                System.out.print("输入有误，请输入0 / 1：");
            }
        }
        mp = new MyPanel(key);
        // 将mp放入Thread中，并启动
        Thread repaintThread = new Thread(mp);
        repaintThread.start();

        this.add(mp); // 窗口添加面板(面板包含绘制出的内容)
        this.addKeyListener(mp); // 添加键盘监听器

        // 窗口要设置得比黑色区域大一些，边框和标题也占用空间
        this.setSize(1300, 950);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        // 在JFrame中增加关闭窗口，保存游戏记录的逻辑
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Record.keepRecord();
                System.exit(0);
            }
        });
    }
}
