package Act2;

public class ClasseFill implements Runnable{

	int espera;
	int num;
	
	ClasseFill(int espera, int num) {
		this.espera = espera;
		this.num = num;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int bucle = espera;
		for(int i = 0; i < bucle; i++) {
			System.out.println("Soc el Thread " + num + " i em queden " + espera + " segons");
			try {
				Thread.sleep(1000);
				espera--;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
