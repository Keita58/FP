package Classes;

public class Arma extends HeroiEquipatDecorator {

	public Arma(HeroiEquipat he) {
		super(he);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String equipar() {
		return super.equipar() + " i m'equipo un arma";
	}
	
	@Override
	public double getPoder() {
		return super.getPoder()+2.5;
	}
}
