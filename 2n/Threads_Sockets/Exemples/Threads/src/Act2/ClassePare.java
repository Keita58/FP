package Act2;

import java.util.ArrayList;
import java.util.Random;

public class ClassePare {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ArrayList<Thread> llistaThread = new ArrayList<>();
		Random r = new Random();
		
		for(int i = 0; i < 10; i++) {
			int num = r.nextInt(11);
			ClasseFill cf = new ClasseFill(num, i+1);
			Thread th = new Thread(cf);
			th.start();
			llistaThread.add(th);
			Thread.sleep(500);
		}
		
		for(Thread th : llistaThread) {
			try {
				th.join();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		System.out.println("Main acabat");
	}

}
