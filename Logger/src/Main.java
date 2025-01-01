import static util.MyLogger.log;

public class Main {

    static class PrintWork implements Runnable {
        private String content;
        private Integer sleepMs;

        public PrintWork(String content, Integer sleepMs){
            this.content = content;
            this.sleepMs = sleepMs;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    log(this.content);
                    Thread.sleep(this.sleepMs);
                }
            } catch (InterruptedException e) {
                e.fillInStackTrace();
            }
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static void main(String[] args) {

        log("Main start");

//        MyThread runnable = new MyThread();
//
//        Thread thread1 = new Thread(runnable);
//        thread1.start();
//        Thread thread2 = new Thread(runnable);
//        thread2.start();
//        Thread thread3 = new Thread(runnable);
//        thread3.start();
//
//        Thread thread4 = new Thread(() -> log("lambda run()"));
//        thread4.start();
//
//        CounterThread counterThread = new CounterThread();
//        counterThread.start();
//
//        Thread counter = new Thread(new CounterRunnable(), "counter");
//        counter.start();

//        Runnable runnable2 = new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    for(int i = 1; i <= 5; i++) {
//                        log("value : " + i);
//                        Thread.sleep(1000);
//                    }
//                } catch (InterruptedException e) {
//                    e.fillInStackTrace();
//                }
//            }
//        };
//        Thread counter2 = new Thread(runnable2);
//        counter2.start();

        PrintWork printA = new PrintWork("A", 1000);
        PrintWork printB = new PrintWork("B", 500);

        Thread threadA = new Thread(printA, "Thread A");
        Thread threadB = new Thread(printB, "Thread B");
        threadA.start();
        threadB.start();

        log("Main end");
    }
}