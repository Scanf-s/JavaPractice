package thread;
import static util.MyLogger.log;

public class MyThread implements Runnable {

    @Override
    public void run() {
        log(Thread.currentThread().getName() + ": run()");
    }
}
