package Objecte;

import Main.MokeponCapturat;

public interface Equipament {
	
	public abstract void equipar(MokeponCapturat mok);

	public abstract void desequipar(MokeponCapturat mok);
	
	public default boolean potEquipar(MokeponCapturat mok) {
		
		if(mok.getEquip() == null && !mok.isDebilitat())
			return true;
		else
			return false;
	}
	
	default boolean equipMalPosat(MokeponCapturat mok) {
		
		if(mok.getObjecte() instanceof Equipament)
			return true;
		else
			return false;
	}
}
