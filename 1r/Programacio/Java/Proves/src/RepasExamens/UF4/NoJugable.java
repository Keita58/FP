package RepasExamens.UF4;

public abstract class NoJugable extends Personatge{
	
	private int apreci;
	private int apreciMaxim;

	public int getApreci() {
		return apreci;
	}

	public void setApreci(int apreci) {
		this.apreci = apreci;
	}

	public int getApreciMaxim() {
		return apreciMaxim;
	}

	public void setApreciMaxim(int apreciMaxim) {
		this.apreciMaxim = apreciMaxim;
	}

	public NoJugable(String nom, Hortalissa preferida, boolean casat) {
		super(nom, preferida);
		this.apreci = 0;
		this.apreciMaxim = 10;
		this.setCasat(casat);
	}
	
	public abstract boolean propostaParella(Granger g);
	
}
