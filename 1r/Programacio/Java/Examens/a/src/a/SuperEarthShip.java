package a;

import java.io.Serializable;

public class SuperEarthShip implements Serializable{

	String name;
	int reinforcements;
	int credits;
	
	public SuperEarthShip(String name, int credits, int reinforcements) {
		super();
		this.name = name;
		this.reinforcements = reinforcements;
		this.credits = credits;
	}

	@Override
	public String toString() {
		return "SuperEarthShip [name=" + name + ", reinforcements=" + reinforcements + ", credits=" + credits + "]";
	}
}
