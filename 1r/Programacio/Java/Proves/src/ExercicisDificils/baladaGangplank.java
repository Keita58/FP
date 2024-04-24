package ExercicisDificils;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class baladaGangplank {
	static boolean recursiu(double[] nPistoles, double midaAsteroide) {
		if(midaAsteroide <= 1)
			return false;
		else {
			for(int i = 0; i < nPistoles.length; i++) {
				double midaAux = midaAsteroide*nPistoles[i];
				if(!recursiu(nPistoles, midaAux)) 
					return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		ohowo.useLocale(Locale.US);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			double midaAsteroide = ohowo.nextDouble();
			double[] nPistoles = new double[ohowo.nextInt()];
			ArrayList<Double> marges = new ArrayList<>();
			
			double min = 1;
			int posMin = -1;
			for(int j = 0; j < nPistoles.length; j++) {
				double aux = ohowo.nextDouble();
				if(aux < min) {
					min = aux;
					posMin = j;
				}
				nPistoles[j] = aux;
			}
			
			double calculs = 1;
			while(calculs < midaAsteroide) {
				calculs /= nPistoles[posMin];
				marges.add(calculs);
			}
			
			int torn = 0;
			boolean res = recursiu(nPistoles, midaAsteroide);
			
			if(res) {
				System.out.println("JO");
			}
			else
				System.out.println("GANGPLANK");
			/*while(!acabat) {
				ArrayList<Integer> posSi = new ArrayList<>();
				int pos = 0;
				for(int j = 0; j < nPistoles.length; j++) {
					boolean exit = false;
					double midaAux = midaAsteroide*nPistoles[j];
					if(midaAux <= 1) {
						pos = j;
						break;
					}
					
					for(int k = 0; k < marges.size() && !exit; k++) {
						switch(k) {
						case 0: 
							if(midaAux <= marges.get(k))
								exit = true;
							break;
						case 1:
							if(midaAux <= marges.get(k) && midaAux > marges.get(k-1)) {
								int num = 0;
								for(int l = 0; l < nPistoles.length; l++) {
									double aux = midaAux*nPistoles[l];
									if(aux <= marges.get(0))
										num++;
								}
								if(num == nPistoles.length)
									posSi.add(j);
							}
							break;
						default:
							break;
						}
					}
				}
				
				min = 100;
				
				for(int j = 0; j < posSi.size(); j++) {
					if(midaAsteroide*nPistoles[posSi.get(j)] < min) {
						min = midaAsteroide*nPistoles[j];
						pos = posSi.get(j);
					}
				}
				
				midaAsteroide *= nPistoles[pos];
				
				if(midaAsteroide <= 1 && torn%2 == 0) {
					System.out.println("JO");
					acabat = true;
				}
				else if(midaAsteroide <= 1 && torn%2 == 1) {
					System.out.println("GANGPLANK");
					acabat = true;
				}
				torn++;
			}*/
		}	
	}
}