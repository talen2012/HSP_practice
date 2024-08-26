package threaduse;

public class Thread01 {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("可用的CPU核心数=" + runtime.availableProcessors());
        int i = 0;
        Cat cat = new Cat();
        cat.start(); // 调用之后，自动调用run方法，不会阻塞
        System.out.println("主线程继续执行，线程名=" + Thread.currentThread().getName());
        while (true) {
            System.out.println("主线程调用" + (++i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { // 线程休眠时可能会抛出异常，为什么，因为线程可能被打断，比如调用了interrupt方法
                throw new RuntimeException(e);
            }

            if (i == 30) {
                break;
            }
        }


    }
}

class Cat extends Thread {
    @Override
    public void run() {
        int i = 0;
        while (true) {
            System.out.println("小猫喵喵叫，小猫" + (++i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (i == 50) {
                break;
            }
        }

    }
}