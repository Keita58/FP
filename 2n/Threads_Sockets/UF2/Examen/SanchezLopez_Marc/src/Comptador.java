import java.util.concurrent.ExecutorService;

public class Comptador implements Runnable{

    ExecutorService exe;

	public Comptador(ExecutorService executorEx) {
		super();
		this.exe = executorEx;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(10000);
            this.exe.shutdownNow();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
