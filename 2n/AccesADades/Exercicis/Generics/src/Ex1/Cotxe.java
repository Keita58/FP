package Ex1;

public class Cotxe {
	
	String matricula;
	String marca;
	String model;
	double amplada;
	double llargada;
	
	public Cotxe(String matricula, String marca, String model, double amplada, double llargada) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.model = model;
		this.amplada = amplada;
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

	public double getAmplada() {
		return amplada;
	}

	public void setAmplada(double amplada) {
		this.amplada = amplada;
	}

	public double getLlargada() {
		return llargada;
	}

	public void setLlargada(double llargada) {
		this.llargada = llargada;
	}

	@Override
	public String toString() {
		return "Cotxe [matricula=" + matricula + ", marca=" + marca + ", model=" + model + ", amplada=" + amplada
				+ ", llargada=" + llargada + "]";
	}
}
