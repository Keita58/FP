package Projecte;

public class Lenticular implements Galaxia {

	String nom;
	String tipus;

	public Lenticular() {
		this.nom = "Lenticular";
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
		return "Lenticular [nom=" + nom + ", tipus=" + tipus + "]";
	}
}
