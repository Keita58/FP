package Act2;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        TCGStore tenda = new TCGStore(12);
        ExecutorService executor = Executors.newFixedThreadPool(6);

        executor.execute(new Consumidor(tenda));
        executor.execute(new Consumidor(tenda));
        executor.execute(new Consumidor(tenda));
        executor.execute(new Productor(tenda));
        executor.execute(new Productor(tenda));
        executor.execute(new Productor(tenda));

        while (true) {
            try {
                if(!executor.awaitTermination(15, TimeUnit.SECONDS)){
                    System.err.println("REINICI DE LA TENDA (15s)");
                    tenda.setSeguent(0);
                    String[] aux = {""};
                    tenda.setCartes(aux);
                    tenda.setId(1);
                    executor.shutdownNow();
                    System.out.println("a");
                    executor.execute(new Productor(tenda));
                    executor.execute(new Productor(tenda));
                    executor.execute(new Productor(tenda));
                    Thread.sleep(3000);
                    executor.execute(new Consumidor(tenda));
                    executor.execute(new Consumidor(tenda));
                    executor.execute(new Consumidor(tenda));
	    		}
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}
