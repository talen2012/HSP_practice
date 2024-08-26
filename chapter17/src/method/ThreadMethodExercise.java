package method;

/**
 * 主线程1s吃一个包子，工十次
 * 第五次之后启动子线程、子线程1s吃一个包子，共十次
 * 子线程执行完再继续执行主线程
 * Runnable接口实现
 */
public class ThreadMethodExercise {
    public static void main(String[] args) throws InterruptedException{
        int cnt = 0;
        Thread.currentThread().setName("爸爸线程");
        Thread t1 = new Thread(new subProcess());
        while (true) {
            System.out.println("主线程吃包子" + (++cnt) + "... " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (cnt == 5) {
                t1.setName("儿子线程");
                t1.start();
//                t1.join();
//                System.out.println("子线程吃完了，主线程继续吃...");
            }

            if (cnt == 10) { // 第十秒的时候，子线程执行了5s中，处于休眠状态
                // 调用t1.interrupt()方法唤醒
                t1.interrupt();
            }

            if (cnt == 15) {
                System.out.println(Thread.currentThread().getName() + "退出了");
                break;
            }
        }
    }
}

class subProcess implements Runnable {
    private int count = 0;
    @Override
    public void run() {
        while (true) {
            System.out.println("子线程吃包子" + (++count) + "... " + Thread.currentThread().getName());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "被唤醒，继续执行");
            }

            if (count == 20) {
                break;
            }
        }
    }
}
