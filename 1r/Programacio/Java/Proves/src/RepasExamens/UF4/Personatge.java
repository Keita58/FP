package RepasExamens.UF4;

public abstract class Personatge {

	private String nom;
	private Hortalissa preferida;
	private boolean casat;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Hortalissa getPreferida() {
		return preferida;
	}
	public void setPreferida(Hortalissa preferida) {
		this.preferida = preferida;
	}
	public boolean isCasat() {
		return casat;
	}
	public void setCasat(boolean casat) {
		this.casat = casat;
	}
	
	public Personatge(String nom, Hortalissa preferida) {
		this.nom = nom; 
		this.preferida = preferida;
		this.casat = false;
	}
	
	@Override
	public String toString() {
		return "Personatge [nom=" + nom + "]";
	}
}
