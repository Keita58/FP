package Projecte;

public class Cano implements Equipament {

	String nom;
	double factor;
	String tipus;

	public Cano() {
		this.nom = "Canó";
		this.factor = 0.33;
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
		return "Cano [nom=" + nom + ", factor=" + factor + ", tipus=" + tipus + "]";
	}
}
