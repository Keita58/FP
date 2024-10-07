package Ex2;

public class main {

	public static void main(String[] args) {
		
		Cotxe cotxe1 = new Cotxe("1234ABC", "Seat", "Ibiza", 4.5,2.0);
		Moto moto1 = new Moto("456DEF", "Ducati", "MULISTRADA V4 S", 2.3);
		Bicicleta bicicleta = new Bicicleta("Dani Fernandez", "Trek", "FX 2", 1.65);
		Patinet patinet = new Patinet("Joan Queralt", "Xiaomi", "Electric Sccoter Pro 2", 29);
		Cotxe cotxe2 = new Cotxe("9876ABC", "Toyota", "Yaris", 3.5,1.5);
		Moto moto2 = new Moto("543DEF", "Suzuki", "SV650 2023", 2.1);
		Garatge garatge1 = new Garatge<>();
		Garatge garatge2 = new Garatge<>();
		garatge1.afegirVehicle(cotxe1);
		garatge1.afegirVehicle(moto1);
		garatge1.afegirVehicle(bicicleta);
		garatge1.mostrarVehicles();
		garatge2.afegirVehicle(cotxe2);
		garatge2.afegirVehicle(moto2);
		garatge2.afegirVehicle(patinet);
		garatge2.mostrarVehicles();
	}
}
