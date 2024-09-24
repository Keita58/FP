package Ex1;

public class Bicicleta {

	String nomPropietari;
	double llarg;
	String marca;
	String model;
	
	public Bicicleta(String nomPropietari, String marca, String model, double llarg) {
		super();
		this.nomPropietari = nomPropietari;
		this.llarg = llarg;
		this.marca = marca;
		this.model = model;
	}

	public String getNomPropietari() {
		return nomPropietari;
	}

	public void setNomPropietari(String nomPropietari) {
		this.nomPropietari = nomPropietari;
	}

	public double getLlarg() {
		return llarg;
	}

	public void setLlarg(double llarg) {
		this.llarg = llarg;
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
		return "Bicicleta [nomPropietari=" + nomPropietari + ", llarg=" + llarg + ", marca=" + marca + ", model="
				+ model + "]";
	}
}
