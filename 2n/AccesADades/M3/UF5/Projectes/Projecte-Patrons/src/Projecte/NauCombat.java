package Projecte;

public class NauCombat implements Enemic{

	String nom;
	int puntsRestar;
	String tipus;

	public NauCombat() {
		this.nom = "Nau de combat";
		this.puntsRestar = -10;
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
		return "NauCombat [nom=" + nom + ", puntsRestar=" + puntsRestar + ", tipus=" + tipus + "]";
	}
}
