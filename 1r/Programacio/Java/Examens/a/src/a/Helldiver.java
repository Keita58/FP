package a;

import java.io.Serializable;
import java.util.ArrayList;

public class Helldiver implements Serializable{

	String name;
	int hp;
	boolean democracy;
	boolean alive;
	ArrayList<Stratagem> stratagems;
	
	public Helldiver(String name, int hp, boolean democracy, boolean alive, ArrayList<Stratagem> stratagems) {
		super();
		this.name = name;
		this.hp = hp;
		this.democracy = democracy;
		this.alive = alive;
		this.stratagems = stratagems;
	}

	@Override
	public String toString() {
		return "Helldiver [name=" + name + ", hp=" + hp + ", democracy=" + democracy + ", alive=" + alive + "]";
	}
	
	
}
