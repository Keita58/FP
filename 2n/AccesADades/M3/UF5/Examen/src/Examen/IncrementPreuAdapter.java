package Examen;

public class IncrementPreuAdapter implements Adaptador {

	double gelat;
	
	public IncrementPreuAdapter(double preuTotal) {
		super();
		this.gelat = preuTotal;
	}

	@Override
	public double getPreu() {
		// TODO Auto-generated method stub
		return gelat*1.21;
	}

}
