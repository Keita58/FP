
public class Enemigo {
	
	public Posicio p;
	public int hp;
	public boolean alive;
	@Override
	public String toString() {
		return "Enemigo [p=" + p + ", hp=" + hp + ", alive=" + alive + "]";
	}
	
	public Enemigo() {
		
	}
	
	public Enemigo(int f, int c, int hp) {
		p = new Posicio(f,c);
		this.hp=hp;
	}

}
