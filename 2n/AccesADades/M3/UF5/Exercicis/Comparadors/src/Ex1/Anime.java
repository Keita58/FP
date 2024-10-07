package Ex1;

public class Anime implements Comparable<Anime> {

	String nom;
	int anyEstrena;
	int mitjanaDuracio;
	
	public Anime(String nom, int anyEstrena, int mitjanaDuracio) {
		super();
		this.nom = nom;
		this.anyEstrena = anyEstrena;
		this.mitjanaDuracio = mitjanaDuracio;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getAnyEstrena() {
		return anyEstrena;
	}

	public void setAnyEstrena(int anyEstrena) {
		this.anyEstrena = anyEstrena;
	}

	public int getMitjanaDuracio() {
		return mitjanaDuracio;
	}

	public void setMitjanaDuracio(int mitjanaDuracio) {
		this.mitjanaDuracio = mitjanaDuracio;
	}

	@Override
	public String toString() {
		return "Anime [nom=" + nom + ", anyEstrena=" + anyEstrena + ", mitjanaDuracio=" + mitjanaDuracio + "]";
	}

	@Override
	public int compareTo(Anime o) {
		return this.getNom().compareTo(o.getNom());
	}
}
