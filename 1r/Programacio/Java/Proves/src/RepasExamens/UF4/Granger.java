package RepasExamens.UF4;

public class Granger extends Personatge implements Fuckable{

	Personatge parella;
	BossaCollita<Hortalissa> bossa;
	
	public Granger(String nom, Hortalissa preferida) {
		super(nom, preferida);
		this.setCasat(false);
		this.parella = null;
	}
	
	public int regalar(NoJugable ng, Objecte o) {
		
		if(o instanceof Llavor) {
			int aux = ng.getApreci();
			if(aux > 1)
				ng.setApreci(aux - 1);
		}
		else if(o instanceof Hortalissa) {
			int aux = ng.getApreci();
			if(ng.getPreferida().nom.equals(o.nom) && ng.getPreferida().getTipus().equals(((Hortalissa) o).getTipus())) {
				if(aux < ng.getApreciMaxim() - 1)
					ng.setApreci(aux + 2);
			}
			else
				if(aux < ng.getApreciMaxim())
					ng.setApreci(aux + 1);
		}
		
		return ng.getApreci();
	}
	
	public void plantar(Conreu c, Llavor l) {
		
		c.conreat = l;
	}
	
	public void regar(Conreu c) {
		
		if(c.conreat != null) {
			c.conreat.setTornsRegada(c.conreat.getTornsRegada() + 1);
		}
	}
	
	public Hortalissa collir(Conreu c) throws GrangerIncompetentException {
		
		if(c.conreat != null && c.conreat.getTornsRegada() >= c.conreat.getTornsPerACreixer()) {
			Hortalissa aux = c.conreat.getHortalissa();
			c.conreat = null;
			bossa.add(aux);
			return aux;
		}
		else if(c.conreat == null) {
			throw new GrangerIncompetentException("Estàs intentat collir un camp sense llavors inútil!!");
		}
		else {
			throw new GrangerIncompetentException("Encara no tens hortalisses, tot son llavors :(");
		}
	}

	@Override
	public void casar(Fuckable parella) {
		
		this.setCasat(true);
		((Personatge)parella).setCasat(true);
		this.parella = (Personatge) parella;
	}
}
