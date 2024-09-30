package Act1;

import java.util.Random;

public class ClassePare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random r = new Random();
		int num = r.nextInt(11);
		
		ClasseFill cf1 = new ClasseFill(num);
		Thread th1 = new Thread(cf1);
		th1.start();
		
		try {
			th1.join();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("Main acabat");
	}

}
