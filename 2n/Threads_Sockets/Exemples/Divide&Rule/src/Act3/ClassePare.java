package Act3;
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
		ExecutorService executor = Executors.newCachedThreadPool();
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Processadors disponibles: " + runtime.availableProcessors());

		for(long i = 0; i < 2000000; i++) {
			llistaNum.add(r.nextInt(2000000));
		}
		int sub = runtime.availableProcessors(); // Per quant hem de subdividir la llista
		List<List<Integer>> nombreLlistes = new ArrayList<>();

		for(int i = 0; i < sub; i++) {
			nombreLlistes.add(llistaNum.subList((i*2000000)/sub, ((i+1)*2000000)/sub));
		}
		long tempsInici = System.nanoTime();
		ArrayList<Future<Integer[]>> futurs = new ArrayList<>();

		for(int i = 0; i < runtime.availableProcessors(); i++) {
			futurs.add(executor.submit(new ClasseFill(nombreLlistes.get(i))));
		}

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
