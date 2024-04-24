
public abstract class Lluitadors extends Actor implements Atacable{

	private int hp;
	private int hpMax;
	private int atac;
	private int nivell;
	private boolean actiu;
	
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

	public int getAtac() {
		return atac;
	}

	public void setAtac(int atac) {
		this.atac = atac;
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

	public Lluitadors() {
		super(10);
		this.hp = 20;
		this.hpMax = 20;
		this.atac = 10;
		this.nivell = 10;
		this.actiu = true;
	}
	
	public Lluitadors(int hp, int hpmax, int atac, int nivell, boolean actiu) {
		super(10);
		this.hp = hp;
		this.hpMax = hpmax;
		this.atac = atac;
		this.nivell = nivell;
		this.actiu = actiu;
	}
	
	public void atacar(Atacable atacat) throws ElsMortsNoAtaquenException, NoAtaquisAlsMortsException {
		
		if(!this.isActiu())
			throw new ElsMortsNoAtaquenException("Els morts no poden atacar!!!");
		else {
			if(atacat instanceof Edificis) {
				if(((Edificis) atacat).getHp() <= 0)
					throw new NoAtaquisAlsMortsException("No es poden atacar als morts!!");
				else {
					((Edificis) atacat).rebreDany(this);
					
					if(((Edificis) atacat).getHp() > 0 && atacat instanceof Edificis_Atacants) {
						this.rebreDany((Edificis_Atacants) atacat);
						
						if(this.getHp() <= 0) {
							this.setHp(0);
							this.setActiu(false);
						}
					}
				}
			}
			else if(atacat instanceof Lluitadors) {
				if(((Lluitadors) atacat).getHp() <= 0)
					throw new NoAtaquisAlsMortsException("No es poden atacar als morts!!");
				else {
					((Lluitadors) atacat).rebreDany(this);
					
					if(((Lluitadors) atacat).getHp() > 0) {
						this.rebreDany(((Lluitadors) atacat));
						
						if(this.getHp() <= 0) {
							this.setHp(0);
							this.setActiu(false);
						}
					}
				}
			}
		}
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
	
	public int compareTo(Lluitadors ll) {

		if(this.getNivell() < ll.getNivell())
			return -1;
		else if(this.getAtac() < ll.getAtac())
			return -1;
		else if(this.isActiu())
			return -1;
		else if(ll.isActiu())
			return 1;
		else return 0;
	}
}
