package RepasExamens.UF4;

public class Objecte {
	
	String nom;
	
	public Objecte(String nom) {
		this.nom = nom;
	}
	
	public int vendre() {
		
		System.out.println("No és un objecte que es pugui vendre");
		return 0;
	}

	@Override
	public String toString() {
		return "Objecte [nom=" + nom + "]";
	}
}
