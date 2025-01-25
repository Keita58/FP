
public class Temp implements Runnable{

	int segons;

	public Temp(int segons) {
		super();
		this.segons = segons;
	}

	@Override
	public void run() {
		try {
			for(int i = segons; i >= 0; i--) {
				System.out.println("Queden " + i + " segons.");
				Thread.sleep(1000);
			}
			synchronized(this) { notifyAll(); } // Avises a tothom per començar la partida
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
