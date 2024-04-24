package Extres.Bucles.Hard;

import java.util.ArrayList;
import java.util.Scanner;

public class bolaProjector {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int distancia = ohowo.nextInt();
			int trossos = ohowo.nextInt();
			int suma = 0;
			ArrayList<Integer> llista = new ArrayList<>();
			
			for(int j = 0; j < trossos; j++) {
				llista.add(ohowo.nextInt());
				suma += llista.get(j);
			}
			
			if(suma == distancia)
				System.out.println("SI");
			else {
				for(int j = 0; j < trossos; j++) {
					suma = llista.get(j);
					if(suma == distancia) {
						System.out.println("SI");
						break;
					}
					for(int k = 0; k < trossos; k++) {
						if(j < k)
							suma += llista.get(k);
						for(int l = k + 1; l < trossos; l++) {
							suma += llista.get(l);
							if(suma == distancia) {
								System.out.println("SI");
								break;
							}
							else {
								suma -= llista.get(l);
							}
						}
					}
				}
			}
		}
	}
}
