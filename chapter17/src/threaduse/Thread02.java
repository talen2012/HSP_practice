package threaduse;

public class Thread02 {
    public static void main(String[] args) {
        Tiger tiger = new Tiger();
        ThreadProxy threadProxy = new ThreadProxy(tiger);
        threadProxy.start();
    }
}

class ThreadProxy implements Runnable {
    private Runnable target;
    @Override
    public void run() {
        if (target != null) {
            target.run();
        }
    }

    public ThreadProxy(Runnable target) {
        this.target = target;
    }

    public void start() {
        start0();
    }

    public void start0() {
        run();
    }
}

class Tiger implements Runnable {
    @Override
    public void run() {
        System.out.println("老虎嗷嗷叫");
    }
}