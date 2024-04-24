import java.io.Serializable;

public class Bill implements Serializable{

	String in;
	String out;
	int inAmmount;
	
	public Bill(String in, String out, int inAmmount) {
		this.in = in; 
		this.out = out;
		this.inAmmount = inAmmount;
	}
	
	@Override
	public String toString() {
		return "Bill [in=" + in + ", out=" + out + ", inAmmount=" + inAmmount + "]";
	}
	
}
