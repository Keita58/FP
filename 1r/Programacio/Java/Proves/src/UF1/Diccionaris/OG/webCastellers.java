package UF1.Diccionaris.OG;

import java.util.HashMap;
import java.util.Scanner;

public class webCastellers {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int parelles = ohowo.nextInt();
			ohowo.nextLine();
			HashMap<String, String> mapa = new HashMap<>();
			for(int j = 0; j < parelles; j++) {
				String dicc = ohowo.nextLine();
				String[] paraules = dicc.split("-");
				mapa.put(paraules[0], paraules[1]);
			}
			String frase = ohowo.nextLine();
			String[] a = frase.split(" ");
			
			for(String paraula : a) {
				if(mapa.containsKey(paraula))
					System.out.print(mapa.get(paraula) + " ");
				else
					System.out.print(paraula + " ");
			}
		}
	}

}
