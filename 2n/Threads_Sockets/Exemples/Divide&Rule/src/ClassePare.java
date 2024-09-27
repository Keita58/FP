import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ClassePare {

	public static void main(String[] args) {
		
		ArrayList<Integer> llistaNum = new ArrayList<>();
		Random r = new Random();
		ExecutorService executor = Executors.newFixedThreadPool(1);
		
		for(long i = 0; i < 2000000000; i++) {
			llistaNum.add(r.nextInt(2000000000));
		}
		long tempsInici = System.nanoTime();
		Future<Integer[]> futur = executor.submit(new ClasseFill(llistaNum));
	}

}
