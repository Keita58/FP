package RepasExamens.UF4;

public class Llavor extends Objecte{

	private int tornsRegada;
	private int tornsPerACreixer;
	private Hortalissa hortalissa;
	
	public int getTornsRegada() {
		return tornsRegada;
	}

	public void setTornsRegada(int tornsRegada) {
		this.tornsRegada = tornsRegada;
	}

	public int getTornsPerACreixer() {
		return tornsPerACreixer;
	}

	public void setTornsPerACreixer(int tornsPerACreixer) {
		this.tornsPerACreixer = tornsPerACreixer;
	}

	public Hortalissa getHortalissa() {
		return hortalissa;
	}

	public void setHortalissa(Hortalissa hortalissa) {
		this.hortalissa = hortalissa;
	}
	
	public Llavor(String nom, int tornsPerACreixer, Hortalissa hortalissa) {
		super(nom);
		this.tornsRegada = 0;
		this.tornsPerACreixer = tornsPerACreixer;
		this.hortalissa = hortalissa;
	}

}
