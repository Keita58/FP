package Ex3;

public class Patinet implements Vehicles {

	String nomPropietari;
	int potencia;
	String marca;
	String model;
	
	public Patinet(String nomPropietari, String marca, String model, int potencia) {
		super();
		this.nomPropietari = nomPropietari;
		this.potencia = potencia;
		this.marca = marca;
		this.model = model;
	}

	public String getNomPropietari() {
		return nomPropietari;
	}

	public void setNomPropietari(String nomPropietari) {
		this.nomPropietari = nomPropietari;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "Patinet [nomPropietari=" + nomPropietari + ", potencia=" + potencia + ", marca=" + marca + ", model="
				+ model + "]";
	}
}
