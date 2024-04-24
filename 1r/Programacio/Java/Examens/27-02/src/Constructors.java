
public class Constructors extends Actor {

	private boolean ocupat;
	
	public boolean isOcupat() {
		return ocupat;
	}

	public void setOcupat(boolean ocupat) {
		this.ocupat = ocupat;
	}

	public Constructors() {
		super(10);
		this.ocupat = true;
	}
	
	public Constructors(boolean ocupat) {
		super(10);
		this.ocupat = ocupat;
	}
}
