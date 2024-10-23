package Examen;

public class Cucurutxo implements Gelats {

	String nom;
	double preu;
	Sabors sabor;
	
	public Cucurutxo(Sabors sabor) {
		super();
		this.nom = "Cucurutxo";
		this.preu = 3;
		this.sabor = sabor;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return this.nom;
	}

	@Override
	public String getDescripcio() {
		// TODO Auto-generated method stub
		return this.nom + " de " + this.sabor;
	}

	@Override
	public double getPreu() {
		// TODO Auto-generated method stub
		return this.preu;
	}

	@Override
	public boolean getIncrement() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Gelats getGelat() {
		// TODO Auto-generated method stub
		return this;
	}

	public void setPreu(double d) {
		// TODO Auto-generated method stub
		this.preu *= d;
	}

	
}
