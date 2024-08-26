package com.talen.draw;

import javax.swing.*;
import java.awt.*;

public class DrawCircle extends JFrame{
    private MyPanel myPanel = null;
    public static void main(String[] args) {
        new DrawCircle();
    }

    // 构造函数
    DrawCircle() {
        // 初始化面板
        myPanel = new MyPanel();
        // 将面板放入窗口
        this.add(myPanel);
        // 设置窗口大小
        this.setSize(400, 800);
        // 设置点击“x"按钮结束程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 显示
        this.setVisible(true);
    }
}

/**
 * 1. 创建一个MyPanel类，继承Jpanel，理解为一个画布
 * 2. Graphics g 把g理解为一个画笔
 * 3. Graphics提供了很多绘图方法
 */

class MyPanel extends JPanel {
    /**
     * 2. Graphics g 把g理解为一个画笔
     * 3. Graphics提供了很多绘图方法
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g); // 调用父类方法完成初始化
        // 画出一个圆形
        g.drawOval(10, 10, 100, 100);
        // 画直线
        g.drawLine(10, 10,110, 110);
        // 画矩形
        g.drawRect(10, 10, 100, 100);
        // 画填充矩形
        // 先设置颜色
        g.setColor(Color.blue);
        g.fillRect(120, 10, 100, 100);
        // 画填充椭圆
        g.setColor(Color.red);
        g.fillOval(10, 120, 100, 50);
        // 画弧形, width，height构成了一个矩形，这个矩形内接一个椭圆，后两个参数通过角度指定椭圆哪一段
        g.drawArc(120, 120, 100, 100, 0, 90);
        g.fillArc(230, 120, 100, 50, 0, 300);

        // 画图片
        // 先获取图片资源 "/bg.png"表示到项目的根目录去获取bn.png图片资源
        Image image = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bg.png"));
        g.drawImage(image, 10, 180, 175, 221, this);
        // 画字符串
        // 设置颜色
        g.setColor(Color.green);
        // 设置字体
        g.setFont(new Font("黑体", Font.BOLD, 50));
        g.drawString("北京你好", 10, 461); //注意这里的(x,y)指的不是左上角，而是左下角
    }
}
