package Projecte;

public class RobotReparador implements Bonus {

	String nom;
	int puntsSumar;
	String tipus;

	public RobotReparador() {
		this.nom = "robot reparador";
		this.puntsSumar = 5;
		this.tipus = "Bonus";
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
	public int puntsASumar() {
		return puntsSumar;
	}

	@Override
	public String toString() {
		return "RobotReparador [nom=" + nom + ", puntsSumar=" + puntsSumar + ", tipus=" + tipus + "]";
	}
}
