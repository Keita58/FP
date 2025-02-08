package Projecte;

public class Meteorit implements Enemic {

	String nom;
	int puntsRestar;
	String tipus;

	public Meteorit(){
		nom = "Meteorit";
		puntsRestar = -15;
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
		return "Meteorit [nom=" + nom + ", puntsRestar=" + puntsRestar + ", tipus=" + tipus + "]";
	}
}
