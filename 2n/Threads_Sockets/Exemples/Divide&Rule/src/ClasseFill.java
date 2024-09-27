import java.util.ArrayList;
import java.util.concurrent.Callable;

public class ClasseFill implements Callable<Integer[]> {

	ArrayList<Integer> llista = new ArrayList<Integer>();
	
	public ClasseFill(ArrayList<Integer> llista) {
		super();
		this.llista = llista;
	}

	@Override
	public Integer[] call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
