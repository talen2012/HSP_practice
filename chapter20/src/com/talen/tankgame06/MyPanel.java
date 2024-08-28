package com.talen.tankgame06;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

/**
 * 1. MyPanel类可以理解为画布，坦克大战的所有内容，都是绘制在MyPanel上的
 * 2. MyPanel类拥有一个Hero坦克，一个敌方坦克集合，和一个爆炸对象集合
 * 3. 坦克拥有坐标、方向、子弹属性
 *    Hero坦克的坐标和方向由键盘控制，敌方坦克的坐标和方向自行变化
 *    敌方坦克和子弹由于要自主移动，所以都是实现了Runnable接口的类，可以用作线程
 *    敌方坦克的run方法实现随机换向、自动前进、和发射子弹
 * 4. 坦克使用shotEnemy()方法生成一个子弹对象，并启动线程，子弹自己碰到墙壁，
 *    或者在MyPanel的run()方法检测到了击中坦克，就退出线程
 * 5. 由于要循环绘制，所以MyPanel类也要实现Runnable接口，作为一个线程启动
 * 6. MyPanel的run方法中，进行击中检查hitCheck
 *    检查己方的每颗子弹有没有击中任一敌方坦克，检查每一敌方的每一子弹是否击中己方
 *    如果击中将子弹和坦克的isAlive都设为false
 * 7. 爆炸集合是用来控制三张爆炸图片的显示的，hitCheck中击中了就记录当前坦克位置
 *    赋给新生成的爆炸对象，在paint()中绘制爆炸效果
 * 8. run方法最后调用repaint, 实际执行的是paint(), 在paint()中绘制坦克和子弹的当前
 *    如果检查到消失的子弹，从集合移除；检测到死亡的坦克，等待它的子弹全部消失，再从集合移除
 */

public class MyPanel extends Panel implements KeyListener, Runnable {
    private Hero hero = null;
    private Vector<EnemyTank> enemyTanks = new Vector<>(); // 敌方坦克
    private Vector<Tank> tanksCollection = new Vector<>(); // 屏幕上所有坦克的集合，用于碰撞检测
    private int enemyTankNum = 5;
    private Image offScreenImage = null; // 双缓冲区
    // 爆炸效果，子弹击中坦克时显示爆炸效果
    // // 子弹击中坦克时，创建一个Bomb对象，加入集合
    private Vector<Bomb> bombs = new Vector<>();
    // // 显示爆炸效果的图片
    private Image img1 = null;
    private Image img2 = null;
    private Image img3 = null;

    MyPanel(int key) {
        // 判断游戏记录是否存在
        File recordFile = new File(Record.getRecordFilePath());
        if (recordFile.exists()) {
            // 读取游戏记录
            Record.getNodeAndTankRec();
        } else {
            // 记录不存在又选择了继续游戏时
            if (key == 1) {
                System.out.println("游戏记录不存在，只能开始新游戏！");
                key = 0;
            }
        }
        // 把坦克集合的引用传给Record类
        Record.setTanksCollection(tanksCollection);
        switch (key) {
            case 0:
                // 创建己方坦克
                hero = new Hero(480, 500, 0);
                hero.setSpeed(10);
                tanksCollection.add(hero);
                hero.setTanksCollection(tanksCollection);
                // 创建敌人坦克
                for (int i = 0; i < enemyTankNum; i++) {
                    EnemyTank enemy = new EnemyTank(140 * (i + 1), 0, 2);
                    enemyTanks.add(enemy);
                    tanksCollection.add(enemy);
                    enemy.setTanksCollection(tanksCollection);
                    new Thread(enemy).start(); // 启动敌方坦克线程
                }
                break;
            case 1:
                Boolean heroExist = false;
                for (Node node: Record.nodes) {
                    if ("Hero".equals(node.type)) {
                        // 根据记录创建己方坦克
                        heroExist = true;
                        hero = new Hero(node.x, node.y, node.dir);
                        hero.setSpeed(10);
                        tanksCollection.add(hero);
                        hero.setTanksCollection(tanksCollection);
                    } else if ("EnemyTank".equals(node.type)) {
                        // 根据记录创建敌方坦克
                        EnemyTank enemy = new EnemyTank(node.x, node.y, node.dir);
                        enemyTanks.add(enemy);
                        tanksCollection.add(enemy);
                        enemy.setTanksCollection(tanksCollection);
                        new Thread(enemy).start(); // 启动敌方坦克线程
                    }
                }
                // 如果记录中没有hero, 说明要么在没有进行游戏的情况下，选择了继续游戏
                // 要么hero被击中，但由于子弹还未消失，所以未重新生成，此时处于isAlive=false状态
                // 无法进入记录，那就新建一个
                // 创建一个新的己方坦克
                if (!heroExist) {
                    hero = new Hero(480, 500, 0);
                    hero.setSpeed(10);
                    tanksCollection.add(hero);
                    hero.setTanksCollection(tanksCollection);
                    break;
                }
        }

        // 初始化爆炸效果图片
        img1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_1.gif"));
        img2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_2.gif"));
        img3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_3.gif"));

        // 播放音乐
        new AePlayWave("chapter20/tankGameMusic.wav").start();
    }

    @Override
    public void paint(Graphics g) {
        // 创建缓冲区
        if (offScreenImage == null) {
            // this.getWidth()是MyPanel的宽度，this.getHeight()是MyPanel的高度
            // MyPanel的宽度高度怎么来的？与HspTankGame03中setSize()什么关系？
            offScreenImage = createImage(this.getWidth(), this.getHeight());
        }

        Graphics gOff = offScreenImage.getGraphics();
        // 绘制游戏区域，默认是黑色
        gOff.fillRect(0, 0, 1000, 750);
        // 展示击杀记录
        showRecord(gOff);

        // 绘制爆炸效果
        for (int i = 0; i < bombs.size(); i++) {
            Bomb b = bombs.get(i);
            if (b.life > 6) {
                gOff.drawImage(img1, b.x, b.y, 60, 60, this);
            } else if (b.life > 3) {
                gOff.drawImage(img2, b.x, b.y, 60, 60, this);
            } else {
                gOff.drawImage(img3, b.x, b.y, 60, 60, this);
            }

            // 让炸弹的生命值减少
            b.lifeDown();
            if (b.life == 0) {
                bombs.remove(i);
            }
        }

        // 绘制Hero坦克，使用封装方法
        if (hero.isAlive) {
            drawTank(hero.getX(), hero.getY(), gOff, hero.getDir(), 1);
        } else if (hero.shots.isEmpty()) {
            // 己方死亡，且子弹已全部消亡，重新创建一个
            // 之前那个hero对象，由于没有引用指向它，被回收
            // 好像子线程没退出，也会被回收
            tanksCollection.remove(hero);
            hero = new Hero(480, 500, 0);
            hero.setSpeed(10);
            tanksCollection.add(hero);
            hero.setTanksCollection(tanksCollection);
        }

        // 绘制己方所有坦克子弹
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if (shot.isAlive) {
                gOff.setColor(Color.yellow);
                gOff.fillRect(shot.x, shot.y, 2, 2);
            } else {
                hero.shots.remove(i);
            }
        }

        // 绘制敌方坦克, 绘制每个坦克的子弹
        // 注意：敌方坦克被击毁后，会从enemyTanks中移除，
        // 子弹也会消失，所以这里不能使用for-each循环
        // 否则会报ConcurrentModificationException异常
        // 也就是在迭代一个集合时，不能同时修改这个集合
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemy = enemyTanks.get(i);
            if (enemy.isAlive) {
                drawTank(enemy.getX(), enemy.getY(), gOff, enemy.getDir(), 0);
            } else if (enemy.shots.isEmpty()) {
                // 似乎将死去的EnemyTank对象从集合中删除，这个对象也被销毁了
                // 所以如果这里移除死去的EnemyTank，由于它的子弹是它的成员变量
                // 所以不能直接移除，而要等待所有子弹消亡，被移除
                enemyTanks.remove(i);
                tanksCollection.remove(enemy);
            }

            for (int j = 0; j < enemy.shots.size(); j++) {
                Shot shot = enemy.shots.get(j);
                if (shot.isAlive) {
                    gOff.setColor(Color.cyan);
                    gOff.fillRect(shot.x, shot.y, 2, 2);
                } else {
                    enemy.shots.remove(j);
                }
            }
        }

        // 将缓冲区的内容绘制到屏幕上
        g.drawImage(offScreenImage, 0, 0, null);

    }

    public void showRecord(Graphics g) {
        // 多次绘制变化的数字时，会重叠
        // 先绘制一个白色背景，覆盖之前的内容
        g.setColor(Color.WHITE);
        g.fillRect(1020, 0, 280, 300);
        //画出玩家的总成绩
        g.setColor(Color.BLACK);
        g.setFont(new Font("黑体", Font.BOLD, 20));

        g.drawString("您本局/累计击毁敌方坦克：", 1020, 30);
        g.drawString(Record.localTanksYouDestroy + " / " + Record.totalTanksYouDestroy, 1080, 100);
        g.drawString("您本局/累计被击毁：", 1020, 160);
        g.drawString( Record.localTimesOfDestroyed + " / " + Record.totalTimesOfDestroyed, 1080, 230);
        drawTank(1020, 60, g, 0, 0);//画出一个敌方坦克
        drawTank(1020, 190, g, 0, 1);//画出一个己方坦克
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    /**
     * 监听键盘awsd键，控制坦克左上右下移动
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) { // 按下w键，向上
            int dir_ = 0;
            // 转向发生碰撞，直接返回
            if (hero.changeDirCollisionDetection(dir_)) {
                return;
            }
            hero.setDir(0);
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D) { // 按下d键，向右
            int dir_ = 1;
            // 转向发生碰撞，直接返回
            if (hero.changeDirCollisionDetection(dir_)) {
                return;
            }
            hero.setDir(1);
            hero.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S) { // 按下s键，向下
            int dir_ = 2;
            // 转向发生碰撞，直接返回
            if (hero.changeDirCollisionDetection(dir_)) {
                return;
            }
            hero.setDir(2);
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) { // 按下a键，向左
            int dir_ = 3;
            // 转向发生碰撞，直接返回
            if (hero.changeDirCollisionDetection(dir_)) {
                return;
            }
            hero.setDir(3);
            hero.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_J) { // 按下j键，发射子弹
            hero.shotEnemy();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 检查己方的每个子弹，是不是击中了敌方坦克
            // 击中，则子弹和敌方坦克的isAlive都被置为false
            for (Shot s : hero.shots) {
                for (EnemyTank enemy : enemyTanks) {
                    hitCheck(s, enemy);
                }
            }

            // 检查每个敌方坦克的每个子弹，是不是击中了我方坦克
            for (EnemyTank enemy : enemyTanks) {
                for (Shot shot : enemy.shots) {
                    hitCheck(shot, hero);
                }
            }

            this.repaint();
        }

    }

    // 专门写一个方法
    // 检查子弹是否击中坦克
    public void hitCheck(Shot s, Tank tank) {
        // 死亡坦克和消失的子弹，其对象的清理，在每100ms调用的repaint()方法里检测和进行
        // 由于repaint()在run()中是在hitCheck后调用的，所以
        // 子弹消失说明已经到了边缘，不用管，子弹因为击中坦克而消失，紧接着就会被清理，不用管)

        // 因为坦克死亡，且子弹未消失之前，坦克对象不会被销毁
        // 因此进行击中检测之前，先确认坦克未死亡
        if (!tank.isAlive) {
            return;
        }
        // 坦克上下方向，和左右方向时，占据的区域不同
        switch (tank.getDir()) {
            case 0:
            case 2:
                if (s.x > tank.getX() && s.x < tank.getX() + 40 && s.y > tank.getY() && s.y < tank.getY() + 60) {
                    s.isAlive = false;
                    tank.isAlive = false;
                    bombs.add(new Bomb(tank.getX(), tank.getY()));
                    if (tank instanceof Hero) {
                        Record.localTimesOfDestroyed++;
                        Record.totalTimesOfDestroyed++;
                    } else if (tank instanceof EnemyTank) {
                        Record.localTanksYouDestroy++;
                        Record.totalTanksYouDestroy++;
                    }
                }
                break;
            case 1:
            case 3:
                if (s.x > tank.getX() && s.x < tank.getX() + 60 && s.y > tank.getY() && s.y < tank.getY() + 40) {
                    s.isAlive = false;
                    tank.isAlive = false;
                    bombs.add(new Bomb(tank.getX(), tank.getY()));
                    if (tank instanceof Hero) {
                        Record.localTimesOfDestroyed++;
                        Record.totalTimesOfDestroyed++;
                    } else if (tank instanceof EnemyTank) {
                        Record.localTanksYouDestroy++;
                        Record.totalTanksYouDestroy++;
                    }
                }
                break;
        }
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
