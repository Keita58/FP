package Objecte;
import Main.*;

public class Arma extends Objecte implements Equipament{

	int atacExtra;
	
	public Arma(String nom) {
		
		super(nom);
		this.atacExtra = 5;
	}

	public void equipar(MokeponCapturat mok) {
		
		mok.setEquip(this);
		mok.setAtk(mok.getAtk() + this.atacExtra);
	}
	
	public void desequipar(MokeponCapturat mok) {
		
		mok.setEquip(null);
		mok.setAtk(mok.getAtk() - this.atacExtra);
	}

	@Override
	public void utilitzar(Mokepon mok) {
		
		this.equipar((MokeponCapturat) mok);
	}
}
