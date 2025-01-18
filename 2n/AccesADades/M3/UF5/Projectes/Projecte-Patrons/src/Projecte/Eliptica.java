package Projecte;

public class Eliptica implements Galaxia {

	String nom;
	String tipus;

	public Eliptica() {
		this.nom = "El·líptica";
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
		return "Eliptica [nom=" + nom + ", tipus=" + tipus + "]";
	}
}
