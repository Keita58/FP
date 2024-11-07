package Act3;

import java.util.Random;

public class ClasseFill implements Runnable{

	int pId;
	
	public ClasseFill(int pId) {
		this.pId = pId;
	}

	@Override
	public void run() {
		Random r = new Random();
		int rand = r.nextInt(11);

		System.out.println("El procés " + this.pId + " li queden " + rand + " segons.");
		int aux = rand;
		try {
			for(int i = 0; i < aux; i++) { 
				Thread.sleep(1000);
				rand--;
			}
		} catch (InterruptedException e) {
			System.err.println("El procés: "+ this.pId+ " és interromput amb el temps restant: " + rand);
		}
		
		System.out.println("El procés "+ this.pId+ " acaba.");		
	}
}
