package Objecte;
import Main.Mokepon;
import Main.MokeponCapturat;

public class Armadura extends Objecte implements Equipament{
	
	int defExtra;

	public Armadura(String nom) {
		
		super(nom);
		this.defExtra = 5;
	}

	public void equipar(MokeponCapturat mok) {
		
		mok.setEquip(this);
		mok.setDef(mok.getDef() + this.defExtra);
	}

	public void desequipar(MokeponCapturat mok) {
		
		mok.setEquip(null);
		mok.setDef(mok.getDef() - this.defExtra);
	}

	@Override
	public void utilitzar(Mokepon mok) {
		
		this.equipar((MokeponCapturat) mok);
	}

}
