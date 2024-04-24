package UF1.Rotar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Rotacio_llista {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		ArrayList<Integer> llista = new ArrayList<>();
		int count = ohowo.nextInt();
		
		for(int i = 0; i < count; i++) {
			int llargada = ohowo.nextInt();
			for(int j = 0; j < llargada; j++) {
				llista.add(ohowo.nextInt());
			}
			int desplaçament = ohowo.nextInt();
			
			if(llargada > desplaçament) {
				Collections.rotate(llista, desplaçament);
			}
			else if(llargada < desplaçament) {
				desplaçament -= llargada;
				Collections.rotate(llista, desplaçament);
			}
			for(int j = 0; j < llista.size(); j++) {
				System.out.print(llista.get(j)+" ");
			}
			llista.removeAll(llista);
			System.out.println();
		}	
	}
}
