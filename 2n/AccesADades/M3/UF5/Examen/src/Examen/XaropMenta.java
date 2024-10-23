package Examen;

public class XaropMenta implements Xarop {

	String nom;
	double preu;
	
	public XaropMenta() {
		super();
		this.nom = "Xarop de Menta";
		this.preu = 1.0;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return this.nom;
	}

	@Override
	public double getPreu() {
		// TODO Auto-generated method stub
		return this.preu;
	}

	@Override
	public String toString() {
		return "XaropMenta [nom=" + nom + ", preu=" + preu + "]";
	}

	@Override
	public void setPreu(double d) {
		// TODO Auto-generated method stub
		this.preu *= d;
	}
}
