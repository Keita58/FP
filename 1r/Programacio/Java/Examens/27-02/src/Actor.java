
public class Actor extends Entitat {

	private int velocitat;
	
	public int getVelocitat() {
		return velocitat;
	}

	public void setVelocitat(int velocitat) {
		this.velocitat = velocitat;
	}

	public Actor() {
		super("Actor");
		this.velocitat = 10;
	}
	
	public Actor(int velocitat) {
		super("Actor");
		this.velocitat = velocitat;
	}
}
