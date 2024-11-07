package Act1;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        TCGStore tenda = new TCGStore(12);
        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.execute(new Consumidor(tenda));
        executor.execute(new Consumidor(tenda));
        executor.execute(new Consumidor(tenda));
        executor.execute(new Productor(tenda));
    }
}
