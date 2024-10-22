package Classes;

public class HeroiEquipatDecorator implements HeroiEquipat {

	HeroiEquipat he;
	
	public HeroiEquipatDecorator(HeroiEquipat he) {
		this.he = he;
	}

	@Override
	public String equipar() {
		return he.equipar();
	}

	@Override
	public double getPoder() {
		return he.getPoder();
	}

	@Override
	public void setPoder(double poder) {
		he.setPoder(poder);
	}

	@Override
	public String getNomMecha() {
		return he.getNomMecha();
	}
}
