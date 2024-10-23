package Examen;

public class Oreo extends GelatDecorator  {

	Gelats gelat;
	String nom;
	double preu;
	
	public Oreo(Gelats gelat) {
		super(gelat);
		this.nom = "Oreo";
		this.gelat = gelat;
		this.preu = 0.75;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescripcio() {
		// TODO Auto-generated method stub
		return super.getDescripcio() + " amb " + this.nom;
	}

	@Override
	public double getPreu() {
		// TODO Auto-generated method stub
		return super.getPreu() + this.preu;
	}

	@Override
	public boolean getIncrement() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setPreu(double d) {
		// TODO Auto-generated method stub
		this.preu*=d;
	}
}
