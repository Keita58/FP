package Projecte;

public class Alien implements Enemic {

	String nom;
	int puntsRestar;
	String tipus;

	public Alien() {
		this.nom = "Alien";
		this.puntsRestar = -7;
		this.tipus = "Enemic";
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
	public int getPuntsARestar() {
		return puntsRestar;
	}

	@Override
	public String toString() {
		return "Alien [nom=" + nom + ", puntsRestar=" + puntsRestar + ", tipus=" + tipus + "]";
	}
}
