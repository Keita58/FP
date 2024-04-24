package UF1.Diccionaris.OG;

import java.util.HashMap;
import java.util.Scanner;

public class desert2 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int max = 0;
			String maxMapa = null;
			HashMap<String, Integer> mapa = new HashMap<>();
			int linies = ohowo.nextInt();
			ohowo.nextLine();
			for(int j = 0; j < linies; j++) {
				StringBuilder mapaBat = new StringBuilder(ohowo.nextLine());
				String mapaText = mapaBat.substring(0, mapaBat.length() - 2);
				String numero = mapaBat.substring(mapaBat.length() - 1);
				int count = Integer.parseInt(numero);
				if(mapa.containsKey(mapaText))
					mapa.put(mapaText, mapa.get(mapaText)+count);
				else
					mapa.put(mapaText, count);
				if(mapa.get(mapaText) > max) {
					max = mapa.get(mapaText);
					maxMapa = mapaText;
				}
			}
			System.out.println(maxMapa);
		}
	}
}
