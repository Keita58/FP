package Main;

public class Atac {

	String nom;
	double poder;  
	Tipus tipus;
	int moviments_maxims;
	int moviments_actuals;
	
	@Override
	public String toString() {
		return "[Nom=" + getNom() + ", Poder=" + getPoder() + ", Tipus=" + getTipus()
				+ ", Moviments_maxims=" + getMoviments_maxims() + ", Moviments_actuals="
				+ getMoviments_actuals() + "]";
	}

	public String getNom() {
		return nom;
	}

	public double getPoder() {
		return poder;
	}

	public Tipus getTipus() {
		return tipus;
	}

	public int getMoviments_maxims() {
		return moviments_maxims;
	}

	public void setMoviments_maxims(int moviments_maxims) {
		this.moviments_maxims = moviments_maxims;
	}

	public int getMoviments_actuals() {
		return moviments_actuals;
	}

	public void setMoviments_actuals(int moviments_actuals) {
		this.moviments_actuals = moviments_actuals;
	}

	public Atac(String nom, Tipus tipus) {
		
		this.nom = nom;
		this.tipus = tipus;
		this.poder = 10;
		this.moviments_actuals = 10;
		this.moviments_maxims = 10;
	}
	
	public Atac(String nom, double poder, Tipus tipus, int mov) {
		
		this.nom = nom;
		this.poder = poder;
		this.tipus = tipus;
		this.moviments_actuals = mov;
		this.moviments_maxims = mov;
		
		if(this.poder < 10)
			this.poder = 10;
		else if(this.poder > 100)
			this.poder = 100;
	}
}
