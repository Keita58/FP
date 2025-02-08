package Projecte;

public class Supernova implements Enemic {

	String nom;
	int puntsRestar;
	String tipus;

	public Supernova(){
		nom = "Supernova";
		puntsRestar = -20;
		tipus = "Enemic";
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
		return "Supernova [nom=" + nom + ", puntsRestar=" + puntsRestar + ", tipus=" + tipus + "]";
	}
}
