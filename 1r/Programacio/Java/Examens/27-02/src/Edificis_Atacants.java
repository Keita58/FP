
public class Edificis_Atacants extends Edificis {

	private int atac;
	
	public int getAtac() {
		return atac;
	}

	public void setAtac(int atac) {
		this.atac = atac;
	}

	public Edificis_Atacants() {
		super(20, 20, 10, true);
		this.atac = 10;
	}
	
	public Edificis_Atacants(int atac) {
		super(20, 20, 10, true);
		this.atac = atac;
	}
}
