package RepasExamens.UF4;

public class PersonatgeNoCasable extends NoJugable{
	
	public PersonatgeNoCasable(String nom, Hortalissa preferida, boolean casat) {
		super(nom, preferida, casat);
	}

	@Override
	public boolean propostaParella(Granger g) {
		
		return false;
	}

}
