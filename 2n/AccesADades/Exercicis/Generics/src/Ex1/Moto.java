package Ex1;

public class Moto {

	String matricula;
	String marca;
	String model;
	double llargada;
	
	public Moto(String matricula, String marca, String model, double llargada) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.model = model;
		this.llargada = llargada;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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

	public double getLlargada() {
		return llargada;
	}

	public void setLlargada(double llargada) {
		this.llargada = llargada;
	}

	@Override
	public String toString() {
		return "Moto [matricula=" + matricula + ", marca=" + marca + ", model=" + model + ", llargada=" + llargada
				+ "]";
	}
}
