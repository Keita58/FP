
public class Guerrers extends Lluitadors {

	private boolean esMultiplica;
	
	public boolean isEsMultiplica() {
		return esMultiplica;
	}

	public void setEsMultiplica(boolean esMultiplica) {
		this.esMultiplica = esMultiplica;
	}

	public Guerrers() {
		super(20, 20, 10, 10, true);
		this.esMultiplica = true;
	}
	
	public Guerrers(boolean esMultiplica) {
		super(20, 20, 10, 10, true);
		this.esMultiplica = esMultiplica;
	}

	@Override
	public String toString() {
		return "Guerrers [getHp()=" + getHp() + ", getNom()=" + getNom() + "]";
	}
}
