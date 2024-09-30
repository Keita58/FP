package Act1;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class ClasseFill implements Callable<Integer[]> {

	ArrayList<Integer> llista = new ArrayList<Integer>();
	int min = Integer.MAX_VALUE;
	int max = Integer.MIN_VALUE;
	
	public ClasseFill(ArrayList<Integer> llista) {
		super();
		this.llista = llista;
	}

	@Override
	public Integer[] call() throws Exception {
		for(int i = 0; i < llista.size(); i++) {
			if(llista.get(i) > max)
				max = llista.get(i);
			if(llista.get(i) < min)
				min = llista.get(i);
		}
		Integer[] aux = {min, max};
		return aux;
	}
}
