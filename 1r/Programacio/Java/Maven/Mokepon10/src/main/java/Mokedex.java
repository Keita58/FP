import java.util.ArrayList;

public class Mokedex {
	
	private String nom_entrenador;
	private ArrayList<Entrada> entrades = new ArrayList<>();
	
	public Mokedex() {
		super();
	}
	
	public Mokedex(String nom_entrenador, ArrayList<Entrada> entrades) {
		super();
		this.nom_entrenador = nom_entrenador;
		this.entrades = entrades;
	}

	public String getNom_entrenador() {
		return nom_entrenador;
	}
	
	public void setNom_entrenador(String nom_entrenador) {
		this.nom_entrenador = nom_entrenador;
	}
	
	public ArrayList<Entrada> getEntrades() {
		return entrades;
	}
	
	public void setEntrades(ArrayList<Entrada> entrades) {
		this.entrades = entrades;
	}

	@Override
	public String toString() {
		return "Mokedex [nom_entrenador=" + nom_entrenador + ", entrades=" + entrades + "]";
	}
}
