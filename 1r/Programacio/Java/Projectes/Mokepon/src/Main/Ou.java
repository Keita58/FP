package Main;
import java.util.Random;

public class Ou {

	private String especie;
	private Tipus tipus;
	private int passesRestants;
	
	public Ou(String especie, Tipus tipus) {
		
		this.especie = especie;
		this.tipus = tipus;
		
		Random r = new Random();
		int aux = r.nextInt(5, 11);
		this.passesRestants = aux;
	}
	
	public void capturar() {
		if(this.passesRestants > 1)
			this.passesRestants--;
		else {
			this.passesRestants--;
			eclosionar(especie, tipus);
		}
	}

	public static Mokepon eclosionar(String especie, Tipus tipus2) {
        
		return new Mokepon(especie, tipus2);
    }
}
