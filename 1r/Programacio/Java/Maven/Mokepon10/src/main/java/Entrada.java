import java.util.ArrayList;

public class Entrada {
    
    private int id;
    private String nom, tipus;
    private double pes;
    private Evolucio evolucions;
    private ArrayList<String> localitzacions = new ArrayList<>();

    public Entrada() {
		super();
	}
    
	public Entrada(int id, String nom, String tipus, double pes, Evolucio evolucions,
			ArrayList<String> localitzacio) {
		super();
		this.id = id;
		this.nom = nom;
		this.tipus = tipus;
		this.pes = pes;
		this.evolucions = evolucions;
		this.localitzacions = localitzacio;
	}

	public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTipus() {
        return tipus;
    }
    
    public void setTipus(String t) {
        this.tipus = t;
    }
    
	public double getPes() {
		return pes;
	}

	public void setPes(double pes) {
		this.pes = pes;
	}

	public Evolucio getEvolucions() {
		return evolucions;
	}

	public void setEvolucions(Evolucio evolucions) {
		this.evolucions = evolucions;
	}

	public ArrayList<String> getLocalitzacio() {
		return localitzacions;
	}

	public void setLocalitzacio(ArrayList<String> localitzacio) {
		this.localitzacions = localitzacio;
	}

	@Override
	public String toString() {
		return "Entrada [id=" + id + ", nom=" + nom + ", tipus=" + tipus + ", pes=" + pes + ", evolucions=" + evolucions
				+ ", localitzacions=" + localitzacions + "]";
	}
}
