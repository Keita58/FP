import java.io.Serializable;

public class Item implements Serializable{

	String name;
	int amount;
	double value;
	
	public Item(String nom, int amount, double value) {
		this.name = nom;
		this.amount = amount;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Item [name=" + name + ", amount=" + amount + ", value=" + value + "]";
	}
	
}
