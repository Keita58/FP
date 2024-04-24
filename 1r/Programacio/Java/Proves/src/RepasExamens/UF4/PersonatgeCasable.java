package RepasExamens.UF4;

public class PersonatgeCasable extends NoJugable implements Fuckable{
	
	public PersonatgeCasable(String nom, Hortalissa preferida, boolean casat) {
		super(nom, preferida, casat);
	}

	@Override
	public boolean propostaParella(Granger g) {
		
		if(this.getApreci() == this.getApreciMaxim()) {
			g.parella = this;
			return true;
		}
		else
			return false;
	}

	@Override
	public void casar(Fuckable parella) {
		
		this.setCasat(true);
		((Personatge)parella).setCasat(true);
	}

}
