package Ex1;

import java.util.ArrayList;
import java.util.List;

public class Garatge<T> {
	List<T> vehicles;

	public Garatge(List<T> vehicles) {
		super();
		this.vehicles = vehicles;
	}
	
	public Garatge() {
		super();
		vehicles = new ArrayList<>();
	}

	public void afegirVehicle(T vehicle) {
		vehicles.add(vehicle);
	}
	
	public void mostrarVehicles() {
		System.out.println("Llista de vehicles al garatge: ");
		for(T vehicle : vehicles) {
			System.out.println(vehicle.toString());
		}
	}
}
