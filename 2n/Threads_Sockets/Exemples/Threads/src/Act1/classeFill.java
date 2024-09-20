package Act1;

public class ClasseFill implements Runnable{

	int espera;
	
	ClasseFill(int espera) {
		this.espera = espera;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int bucle = espera;
		for(int i = 0; i < bucle; i++) {
			System.out.println("Queden " + espera + " segons");
			try {
				Thread.sleep(1000);
				espera--;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
