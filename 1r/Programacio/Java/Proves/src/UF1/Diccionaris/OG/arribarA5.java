package UF1.Diccionaris.OG;

import java.util.HashMap;
import java.util.Scanner;

public class arribarA5 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		HashMap<String, Integer> mapa = new HashMap<>();
		String nomGuanyador = "NO";
		boolean enter = true;
		
		while(true) {
			String nom = ohowo.nextLine();
			if(nom.equals("xxx"))
				break;
			else {
				if(mapa.containsKey(nom)) {
					mapa.put(nom, mapa.get(nom)+1);
					if(mapa.get(nom) == 5 && enter) {
						nomGuanyador = nom;
						enter = false;
					}
				}
				else
					mapa.put(nom, 1);
				
			}
		}
		
		System.out.println(nomGuanyador);
	}
}
