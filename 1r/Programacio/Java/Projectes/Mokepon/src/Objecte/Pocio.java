package Objecte;
import Main.*;

public class Pocio extends Objecte{

	public int hp_curada;
	
	public Pocio(String nom, int hp) {
		super(nom);
		this.hp_curada = hp;
	}

	@Override
	public void utilitzar(Mokepon mok) {
		
		int vida = mok.getHp_actual();
		if(vida > 0 && this.quantitat >= 1) {
			if(vida + this.hp_curada > mok.getHp_max())
				mok.setHp_actual(mok.getHp_max());
			else
				mok.setHp_actual(vida + this.hp_curada);
			
			this.quantitat--;
		}
	}

	@Override
	public String toString() {
		return "Pocio [hp_curada=" + hp_curada + ", nom=" + nom + "]";
	}

}
