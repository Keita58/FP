
public class Arqueres extends Lluitadors {

	private int distancia;
	
	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public Arqueres() {
		super(20, 20, 10, 10, true);
		this.distancia = 10;
	}
	
	public Arqueres(int distancia) {
		super(20, 20, 10, 10, true);
		this.distancia = distancia;
	}

	@Override
	public String toString() {
		return "Arqueres [getHp()=" + getHp() + ", getNom()=" + getNom() + "]";
	}

	
}
