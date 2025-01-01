package thread.start;
import static util.MyLogger.log;
public class CounterThread extends Thread{

    @Override
    public void run(){
        try {
            for (int i = 1; i <= 5; i++) {
                Thread.sleep(1000);
                log("value : " + i);
            }
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }
    }
}
