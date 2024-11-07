package Act2;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TCGStore tenda = new TCGStore(12);
        ExecutorService executor = Executors.newFixedThreadPool(6);

        executor.execute(new Consumidor(tenda));
        executor.execute(new Consumidor(tenda));
        executor.execute(new Consumidor(tenda));
        executor.execute(new Productor(tenda));
        executor.execute(new Productor(tenda));
        executor.execute(new Productor(tenda));

        while (true) {
            Thread.sleep(15000);
            System.err.println("REINICI DE LA TENDA (15s)");
            tenda = new TCGStore(12);
            executor.shutdownNow();
            Thread.sleep(3000);
            executor = Executors.newFixedThreadPool(6);
            executor.execute(new Productor(tenda));
            executor.execute(new Productor(tenda));
            executor.execute(new Productor(tenda));
            Thread.sleep(3000);
            executor.execute(new Consumidor(tenda));
            executor.execute(new Consumidor(tenda));
            executor.execute(new Consumidor(tenda));	
        }
    }
}
