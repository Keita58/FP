
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
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
