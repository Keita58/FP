import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class Michilover implements Runnable {

    CountDownLatch cdl;
    Map<String, Integer> michis;

    public Michilover(CountDownLatch cdl, Map<String, Integer> michis) {
        this.cdl = cdl;
        this.michis = michis;
    }

    @Override
    public void run() {
        try {
            cdl.countDown();
            cdl.await();
            GameController.incrementarMichitokens(50);
            michis.remove("Michilover");
        }
        catch (Exception e) {

        }
    }

}
