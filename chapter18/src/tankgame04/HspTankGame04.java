package tankgame04;

import javax.swing.*;

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
 */
public class HspTankGame04 extends JFrame {
    private MyPanel mp = null;

    public static void main(String[] args) {
        HspTankGame04 hspTankGame04 = new HspTankGame04();
    }

    HspTankGame04() {
        mp = new MyPanel();
        // 将mp放入Thread中，并启动
        Thread repaintThread = new Thread(mp);
        repaintThread.start();

        this.add(mp); // 窗口添加面板(面板包含绘制出的内容)
        this.addKeyListener(mp); // 添加键盘监听器

        // 窗口要设置得比黑色区域大一些，边框和标题也占用空间
        this.setSize(1200, 950);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
