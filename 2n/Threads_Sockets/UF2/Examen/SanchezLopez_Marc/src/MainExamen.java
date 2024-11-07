import java.util.ArrayList;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainExamen {

    static int futursAcabats = 0;
    static ExecutorService executor = Executors.newFixedThreadPool(4);
    static int[] existencies;

    public static void main(String[] args) {
        existencies = new int[4];
        existencies[0] = 12;
        existencies[1] = 10;
        existencies[2] = 7;
        existencies[3] = 3;
        ExecutorService executorComptador = Executors.newFixedThreadPool(1);
        ArrayList<Future<Alumne>> futurs = new ArrayList<>();
        SortedSet<Alumne> res = new TreeSet<>();
        Random r = new Random();

        for(int i = 0; i < 15; i++) {
            futurs.add(executor.submit(new Cursa(new Alumne(i, r.nextInt(10, 110)), existencies)));
        }
        executor.shutdown();
        //executorComptador.submit(new Comptador(executor));
        //executorComptador.shutdown();
        
        for(Future<Alumne> futur : futurs) {
			try {
                if(executor.isShutdown()) {
                    res.add(futur.get());
                }
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
        
        for(Alumne a : res) {
            System.out.println("Alumne: " + a.getNom() + ", Velocitat: " + a.getVelocitat());
        }

        System.out.println("Existències dels potenciadors:");
            System.out.println("Potenciador petit: " + existencies[0]);
            System.out.println("Potenciador mitjà: " + existencies[1]);
            System.out.println("Potenciador gran: " + existencies[2]);
            System.out.println("Potenciador súper: " + existencies[3]);
    }

    public static synchronized void sumaAcabat() {
        futursAcabats++;
        if(futursAcabats == 14) {
            executor.shutdownNow();
        }
    }

    public static synchronized void restarExistencies(int potenciador) {
        existencies[potenciador]--;
    }
}
