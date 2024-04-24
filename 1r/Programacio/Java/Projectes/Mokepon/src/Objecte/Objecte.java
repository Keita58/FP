package Objecte;

import java.io.Serializable;

import Main.*;

public abstract class Objecte implements Serializable{

	protected String nom;
	protected int quantitat;
	
	public String getNom() {
		return nom;
	}

	public int getQuantitat() {
		return quantitat;
	}

	public Objecte(String nom) {
		this.nom = nom;
		this.quantitat = 1;
	}
	
	public void obtenir(int numObjectes) {
		this.quantitat += numObjectes;
	}
	
	public void donar(MokeponCapturat mok) {
		mok.setObjecte(this);
	}
	
	public abstract void utilitzar(Mokepon mok);
}
