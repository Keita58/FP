package a;

import java.io.Serializable;

public class Stratagem implements Serializable{

	String name;
	int price;
	boolean equipped;
	
	public Stratagem(String name, int price, boolean equipped) {
		super();
		this.name = name;
		this.price = price;
		this.equipped = equipped;
	}

	@Override
	public String toString() {
		return "Stratagem [name=" + name + ", price=" + price + ", equipped=" + equipped + "]";
	}
}
