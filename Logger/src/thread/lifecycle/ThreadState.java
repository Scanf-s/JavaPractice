package thread.lifecycle;

import static util.MyLogger.log;

public class ThreadState {

    private static class MyRunnable implements Runnable {

        @Override
        public void run() {
            try {
                log("Start");
                log("myThread.state2 = " + Thread.currentThread().getState()); // RUNNABLE
                log("Goto sleep()");
                Thread.sleep(3000);
                log("Awake");
                log("myThread.state4 = " + Thread.currentThread().getState()); // RUNNABLE
                log("sleep() end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyRunnable(), "myThread");
        log("myThread.state1 = " + thread.getState()); // NEW
        log("myThread.start()");
        thread.start();
        Thread.sleep(1000);
        log("myThread.state3 = " + thread.getState()); // TIMED_WAITING
        Thread.sleep(5000);
        log("myThread.state5 = " + thread.getState()); // TERMINATED
    }
}
