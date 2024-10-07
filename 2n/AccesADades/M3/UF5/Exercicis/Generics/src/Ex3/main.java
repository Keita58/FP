package Ex3;

import java.util.ArrayList;
import java.util.List;

public class main {

	public static void main(String[] args) {
		List<? extends Number> mates = new ArrayList<>(); //* No es poden afegir dades directament!! */
		List<Integer> integers = new ArrayList<>();
		integers = (List<Integer>) mates;
		integers.add(3);
		integers.add(20);
		integers.add(-1);
		integers.add(-9);
		System.out.println(mitjana(mates));

		mates.clear();

		List<Float> floats = new ArrayList<>();
		floats = (List<Float>) mates;
		floats.add(3f);
		floats.add(3.9f);
		floats.add(-4.6f);
		System.out.println(mitjana(mates));
	}

	public static Number mitjana(List<? extends Number> nombres) {
		double mitjana = 0.0;
		
		for(Number num : nombres) {
			if(num instanceof Integer)
				mitjana += (Integer)num;
			else if(num instanceof Float)
				mitjana += (Float)num;
			else if(num instanceof Double)
				mitjana += (Double)num;
		}

		return mitjana/nombres.size();
	}
}
