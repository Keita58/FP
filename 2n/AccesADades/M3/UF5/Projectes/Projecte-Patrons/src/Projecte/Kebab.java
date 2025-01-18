package Projecte;

public class Kebab implements Recurs{

	String nom;
	double puntsSumar;
	String tipus;

	public Kebab(){
		this.nom = "Kebab";
		this.puntsSumar = 25.0;
		this.tipus = "Recurs";
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public String getTipus() {
		return tipus;
	}

	@Override
	public double puntsASumar() {
		return puntsSumar;
	}

	@Override
	public String toString() {
		return "Kebab [nom=" + nom + ", puntsSumar=" + puntsSumar + ", tipus=" + tipus + "]";
	}
}
