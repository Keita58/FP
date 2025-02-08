package Projecte;

public class Espiral implements Galaxia{

	String nom;
	String tipus;

	public Espiral() {
		this.nom = "Espiral";
		this.tipus = "Galàxia";
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
	public String toString() {
		return "Espiral [nom=" + nom + ", tipus=" + tipus + "]";
	}
}
