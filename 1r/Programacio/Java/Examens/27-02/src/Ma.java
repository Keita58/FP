
public class Ma<T extends Lluitadors, Constructors> {

	private T primer;
	private T segon;
	private T tercer;
	private T quart;
	private T cinque;
	
	public void obtenir(T a) {
		
		if(primer == null)
			primer = a;
		else
			if(segon == null)
				segon = a;
			else
				if(tercer == null)
					tercer = a;
				else
					if(quart == null)
						quart = a;
					else
						if(cinque == null)
							cinque = a;
							
	}
	
	public void jugar(int index) {
		switch(index) {
		case 1:
			primer = null;
			break;
		case 2:
			segon = null;
			break;
		case 3:
			tercer = null;
			break;
		case 4:
			quart = null;
			break;
		case 5:
			cinque = null;
			break;
		}
	}
}
