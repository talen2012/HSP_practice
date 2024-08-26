package syn;

public class SellTicket {
    public static void main(String[] args) {
        // SellTicket01 s1 = new SellTicket01();
        // SellTicket01 s2 = new SellTicket01();
        // SellTicket01 s3 = new SellTicket01();
        //
        // s1.start();
        // s2.start();
        // s3.start();

        // Thread构造函数中可以传入同一个实现了Runnable接口的对象
        // 此时该对象的数据对各子线程是共享的

        // SellTicket02 sellTicket02 = new SellTicket02();
        // Thread t1 = new Thread(sellTicket02);
        // Thread t2 = new Thread(sellTicket02);
        // Thread t3 = new Thread(sellTicket02);
        // System.out.println("t2线程的状态 " + t2.getState());
        // t1.start();
        // t2.start();
        // t3.start();

        // 使用synchronized关键字实现线程同步
        SellTicket02 sellTicket02 = new SellTicket02();
        Thread t1 = new Thread(sellTicket02);
        Thread t2 = new Thread(sellTicket02);
        Thread t3 = new Thread(sellTicket02);
        System.out.println("t2线程的状态 " + t2.getState());
        t1.start();
        t2.start();
        t3.start();
    }

}

class SellTicket01 extends Thread {
    private static int ticketNum = 50; // 多个类对象共享，设置为类的静态属性
    private static Boolean loop = true;           // 循环控制变量

    public void sell() {
        // 继承Thread类这种方式，可以传入xxx.class这是所有类的对象共享的
        synchronized (SellTicket01.class) {
            if (ticketNum <= 0) {
                System.out.println("票已售完！");
                loop = false;
                return;
            }
            System.out.println("窗口 " + Thread.currentThread().getName() +
                    " 售出一张票 剩余票数=" + (--ticketNum));
        }
    }

    @Override
    public void run() {
        while (loop) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sell();
        }
    }
}

class SellTicket02 implements Runnable {
    private int ticketNum = 50; // 多个类对象共享，设置为类的静态属性
    private Boolean loop = true;           // 循环控制变量
    private Object o = new Object();

    public void sell() {
        // 不一定传入this，只要是同一个对象就行
        // 这里使用的是Runnable这种方式，只创建一个SellTicket02对象
        // 让后传给多个Thread的构造函数
        // 即多线程共用一个对象，所以this, o都是同一个
        synchronized (/*this*/ o) {
            if (ticketNum <= 0) {
                System.out.println("票已售完！");
                loop = false;
                return;
            }
            System.out.println("窗口 " + Thread.currentThread().getName()
                    + " 售出一张票 剩余票数=" + (--ticketNum));
        }
    }

    @Override
    public void run() {
        while (loop) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sell();
        }
    }
}