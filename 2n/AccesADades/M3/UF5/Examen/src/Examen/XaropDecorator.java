package Examen;

public class XaropDecorator extends GelatDecorator  {

	Xarop xarop;
	Gelats gelat;
	
	public XaropDecorator(Gelats gelat, Xarop xarop) {
		super(gelat);
		this.xarop = xarop;
		this.gelat = gelat;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescripcio() {
		// TODO Auto-generated method stub
		return super.getDescripcio() + " amb " + this.xarop.getNom();
	}

	@Override
	public double getPreu() {
		// TODO Auto-generated method stub
		return super.getPreu() + this.xarop.getPreu();
	}

	@Override
	public boolean getIncrement() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setPreu(double d) {
		// TODO Auto-generated method stub
		this.xarop.setPreu(d);
	}
}
