import java.util.Objects;

public class Edificis extends Entitat implements Atacable{

	private int hp;
	private int hpMax;
	private int nivell;
	private boolean actiu;
	Tipus tipus;
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public int getNivell() {
		return nivell;
	}

	public void setNivell(int nivell) {
		this.nivell = nivell;
	}

	public boolean isActiu() {
		return actiu;
	}

	public void setActiu(boolean actiu) {
		this.actiu = actiu;
	}

	public Tipus getTipus() {
		return tipus;
	}

	public void setTipus(Tipus tipus) {
		this.tipus = tipus;
	}

	public Edificis() {
		super("Edificis");
		this.hp = 20;
		this.hpMax = 20;
		this.nivell = 10;
		this.actiu = true;
	}
	
	public Edificis(int hp, int hpmax, int nivell, boolean actiu) {
		super("Edificis");
		this.hp = hp;
		this.hpMax = hpmax;
		this.nivell = nivell;
		this.actiu = actiu;
	}

	@Override
	public void rebreDany(Lluitadors lluitador) {
		
		if(lluitador instanceof Guerrers) {
			if(lluitador.isActiu())
				this.hp -= lluitador.getAtac()*2;
			else
				this.hp -= lluitador.getAtac();
			
			if(this.hp <= 0) {
				this.hp = 0;
				this.actiu = false;
			}
		}
	}
	
	@Override
	public void rebreDany(Edificis_Atacants edifici) {
		
		if(edifici.isActiu())
			this.hp -= edifici.getAtac()*2;
		else
			this.hp -= edifici.getAtac();
		
		if(this.hp <= 0) {
			this.hp = 0;
			this.actiu = false;
		}
	}

	@Override
	public String toString() {
		return "Edificis [hp=" + hp + ", getNom()=" + getNom() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(actiu, hpMax, nivell, tipus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edificis other = (Edificis) obj;
		return this.getNom() == other.getNom() && hpMax == other.hpMax && nivell == other.nivell && tipus == other.tipus;
	}
	
}
