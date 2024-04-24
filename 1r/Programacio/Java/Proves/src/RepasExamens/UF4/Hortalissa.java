package RepasExamens.UF4;

import java.util.Objects;

public class Hortalissa extends Objecte implements Comparable<Hortalissa>{

	private Tipus tipus;
	private int preuVenda;
	
	public Tipus getTipus() {
		return tipus;
	}

	public void setTipus(Tipus tipus) {
		this.tipus = tipus;
	}

	public int getPreuVenda() {
		return preuVenda;
	}

	public void setPreuVenda(int preuVenda) {
		this.preuVenda = preuVenda;
	}

	public Hortalissa(String nom, Tipus tipus, int preuVenda) {
		super(nom);
		this.tipus = tipus;
		this.preuVenda = preuVenda;
	}

	@Override
	public int vendre() {
		
		System.out.println("Sha venut " + this.nom + " per " + this.preuVenda);
		return this.preuVenda;
	}

	@Override
	public String toString() {
		return "Hortalissa [tipus=" + tipus + ", nom=" + nom + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(tipus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hortalissa other = (Hortalissa) obj;
		return (this.nom.equals(other.nom) &&  this.tipus == other.tipus);
	}

	@Override
	public int compareTo(Hortalissa o) {

		if(o.preuVenda != this.preuVenda)
			return this.preuVenda - o.preuVenda;
		else
			return this.tipus.ordinal() - o.tipus.ordinal();
	}
}
