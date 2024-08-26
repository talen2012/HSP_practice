package homework;

import java.util.Scanner;

/**
 * 启动两个线程
 * 第一个线程循环打印100以内的随机数
 * 第二个线程接收键盘输入，按下Q时，终止第一个线程
 */
public class Homework01 {
    public static void main(String[] args) {
        A a = new A();
        B b = new B(a);

        new Thread(b).start();
        a.start();
    }
}

class A extends Thread {
    private boolean loop = true;

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    @Override
    public void run() {
        while (loop) {
            System.out.println("100以内随机数" + (int)(Math.random() * 100));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("A线程退出...");
    }
}

class B implements Runnable {
    private A a = null;
    private Scanner myScanner = new Scanner(System.in);

    public B(A a) {
        this.a = a;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("按Q停止:");
            char key = myScanner.next().toUpperCase().charAt(0);
            if (key == 'Q') {
                System.out.println("线程B退出...");
                a.setLoop(false);
                break;
            }
        }
    }
}
