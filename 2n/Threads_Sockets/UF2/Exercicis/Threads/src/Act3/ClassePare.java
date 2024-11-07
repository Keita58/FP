package Act3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ClassePare {

	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			//Executem el Executor amb el nostre Runnable
			executor.execute(new ClasseFill(i));
		}
		
		executor.shutdown(); //<- Necessari per tancar el codi quan tots els threads hagin acabat abans del try catch
		
		try {
			Thread th = new Thread(new Temp(5)); //Això és necessari perquè no ho posem amb l'executor, ha d'anar a part.
			th.start();
			if(!executor.awaitTermination(5, TimeUnit.SECONDS)){
				System.out.println("Han passat 5 segons i finalitzem els fils");
				executor.shutdownNow();
			}
			if(!executor.awaitTermination(1, TimeUnit.SECONDS))
				System.err.println("ERROR MÀXIM PERQUÈ HAURIA D'HAVER ACABAT");
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
