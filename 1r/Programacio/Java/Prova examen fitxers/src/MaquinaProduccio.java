import java.io.Serializable;

public class MaquinaProduccio implements Serializable{

	int usos;
	int usosMax;
	String materialReparacio;
	
	public MaquinaProduccio(int usos, int usosMax, String materialReparacio) {
		this.usos = usos;
		this.usosMax = usosMax;
		this.materialReparacio = materialReparacio;
	}

	@Override
	public String toString() {
		return "MaquinaProduccio [usos=" + usos + ", usosMax=" + usosMax + ", materialReparacio=" + materialReparacio
				+ "]";
	}
}
