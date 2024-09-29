package Act1;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ClassePare {

	public static void main(String[] args) {
		
		ArrayList<Integer> llistaNum = new ArrayList<>();
		Random r = new Random();
		ExecutorService executor = Executors.newFixedThreadPool(1);

		for(long i = 0; i < 2000000; i++) {
			llistaNum.add(r.nextInt(2000000));
		}
		long tempsInici = System.nanoTime();
		Future<Integer[]> futur = executor.submit(new ClasseFill(llistaNum));
		long tempsFinal = 0;
		Integer[] res = {};
		executor.shutdown();

		try{
			if(executor.isShutdown()) {
				tempsFinal = System.nanoTime();
				res = futur.get();
			}
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Temps trigat en calcular: " + (tempsFinal - tempsInici) + " nanosegons (o " + (tempsFinal - tempsInici)*(10e-9) + " segons)");
		System.out.println("El mínim és " + res[0] + " i el màxim és " + res[1]);
	}
}
