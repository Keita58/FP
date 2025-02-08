package Projecte;

public class PortaEspacial implements Bonus{

	String nom;
	int puntsSumar;
	String tipus;

	public PortaEspacial() {
		this.nom = "Porta espacial";
		this.puntsSumar = 2;
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
		return "PortaEspacial [nom=" + nom + ", puntsSumar=" + puntsSumar + ", tipus=" + tipus + "]";
	}
}
