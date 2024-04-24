package a;

import java.io.Serializable;

public class Arma implements Serializable{

	String name;
	int price;
	double damage;
	
	public Arma(String name, int price, double damage) {
		super();
		this.name = name;
		this.price = price;
		this.damage = damage;
	}

	@Override
	public String toString() {
		return "Arma [name=" + name + ", price=" + price + ", damage=" + damage + "]";
	}
}
