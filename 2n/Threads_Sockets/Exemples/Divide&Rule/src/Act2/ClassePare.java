package Act2;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ClassePare {

	public static void main(String[] args) {
		
		List<Integer> llistaNum = new ArrayList<>();
		Random r = new Random();
		ExecutorService executor = Executors.newFixedThreadPool(2);

		for(long i = 0; i < 2000000; i++) {
			llistaNum.add(r.nextInt(2000000));
		}
		List<Integer> llista1 = llistaNum.subList(0, 1000000);
		List<Integer> llista2 = llistaNum.subList(1000000, 2000000);
		long tempsInici = System.nanoTime();
		ArrayList<Future<Integer[]>> futurs = new ArrayList<>();

		futurs.add(executor.submit(new ClasseFill(llista1)));
		futurs.add(executor.submit(new ClasseFill(llista2)));
		
		long tempsFinal = 0;
		ArrayList<Integer[]> res = new ArrayList<>();
		executor.shutdown();

		for(Future<Integer[]> futur : futurs) {
			try {
				if (executor.isShutdown()) {
					tempsFinal = System.nanoTime();
					res.add(futur.get());
				}
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for(Integer[] nums : res) {
			if(nums[0] < min)
				min = nums[0];
			if(nums[1] > max)
				max = nums[1];
		}
		

		System.out.println("Temps trigat en calcular: " + (tempsFinal - tempsInici) + " nanosegons (o " + (tempsFinal - tempsInici)*(10e-9) + " segons)");
		System.out.println("El mínim és " + min + " i el màxim és " + max);
	}
}
