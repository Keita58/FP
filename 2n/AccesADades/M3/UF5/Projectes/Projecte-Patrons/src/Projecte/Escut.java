package Projecte;

public class Escut implements Equipament {

	String nom;
	double factor;
	String tipus;

	public Escut() {
		this.nom = "Escut protector";
		this.factor = 0.75;
		this.tipus = "Equipament";
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
	public double getFactor() {
		return factor;
	}

	@Override
	public String toString() {
		return "Escut [nom=" + nom + ", factor=" + factor + ", tipus=" + tipus + "]";
	}

}
