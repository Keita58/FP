package Part4;

import Core.Sprite;

public class UI {

	public static UI instance = null;
	public Puntuacio punts;
	public Vides vida;
	public Sprite temps;
	
	//en un singleton el Consturctor es privat
	private UI(Puntuacio p, Vides v, Temps t) {
	    //segurament aquí s’hauria d’instanciar també puntiació
		this.punts = p;
		this.vida = v;
		this.temps = t;
		instance = this;
	}
	
	public static UI getInstance(Puntuacio p, Vides v, Temps t) {
	    //si no existeix el creem
	    if(instance == null) {
	        instance = new UI(p, v, t);
	    }
	    //tant si ja existia com si l'hem creat, tornem instance
	    return instance;
	}
}
