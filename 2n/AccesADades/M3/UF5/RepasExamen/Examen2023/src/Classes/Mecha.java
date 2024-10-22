package Classes;

public class Mecha extends HeroiEquipatDecorator {

	public Mecha(HeroiEquipat he) {
		super(he);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String equipar() {
		return super.equipar() + " i entro al mecha " + super.getNomMecha();
	}
	
	@Override
	public double getPoder() {
		return super.getPoder()*2;
	}
}
