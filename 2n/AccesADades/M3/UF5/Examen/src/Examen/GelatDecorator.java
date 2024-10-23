package Examen;

public abstract class GelatDecorator implements Gelats {

	Gelats gelat;
	
	public GelatDecorator(Gelats gelat) {
		this.gelat = gelat;
	}
	
	@Override
    public String getDescripcio() {
		return gelat.getDescripcio();
    }
	
	 @Override
	public Gelats getGelat() {
		 return gelat.getGelat();
	}
	 
	 public double getPreu() {
		 return gelat.getPreu();
	 }
}
