package Projecte;

public class CristallEnergia implements Recurs {
	
	String nom;
	double puntsSumar;
	String tipus;
	
	public CristallEnergia() {
		this.nom = "Cristall d'energia";
		this.puntsSumar = 10;
		this.tipus = "Recurs";
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
	public double puntsASumar() {
		return puntsSumar;
	}

	@Override
	public String toString() {
		return "CristallEnergia [nom=" + nom + ", puntsSumar=" + puntsSumar + ", tipus=" + tipus + "]";
	}

}
