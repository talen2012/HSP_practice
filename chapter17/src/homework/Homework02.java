package homework;

public class Homework02 {
    public static void main(String[] args) {
        WithDraw withDraw = new WithDraw();
        new Thread(withDraw).start();
        new Thread(withDraw).start();
    }
}

class WithDraw implements Runnable {
    private int balance = 10000;

    @Override
    public void run() {
        while (true) {
            synchronized (this) { // 非公平锁，可能一直都是一个线程获取到了锁
                if (balance <= 0) {
                    System.out.println("余额为0！");
                    break;
                }
                balance -= 1000;
                System.out.println(Thread.currentThread().getName() + " 取出100元，余额=" + balance);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
