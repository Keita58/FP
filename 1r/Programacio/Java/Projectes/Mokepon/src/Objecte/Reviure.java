package Objecte;
import Main.*;

public class Reviure extends Objecte{

	public Reviure() {
		super("Reviure");
	}

	@Override
	public void utilitzar(Mokepon mok) {
		
		if(mok.getHp_actual() == 0 && this.quantitat >= 1) {
			mok.debilitarse();
			mok.setHp_actual(1);
			this.quantitat--;
		}
	}

	@Override
	public String toString() {
		return "Reviure [nom=" + nom + "]";
	}
	
}
