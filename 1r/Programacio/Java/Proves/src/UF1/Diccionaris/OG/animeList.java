package UF1.Diccionaris.OG;

import java.util.HashMap;
import java.util.Scanner;

public class animeList {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int linies = ohowo.nextInt();
			ohowo.nextLine();
			float mitjana = 0;
			HashMap<String, Integer> mapa = new HashMap<>();
			for(int j = 0; j < linies; j++) {
				String anime = ohowo.nextLine();
				String[] a = anime.split("-");
				mitjana += Integer.parseInt(a[1]);
				mapa.put(a[0], Integer.parseInt(a[1]));
			}
			mitjana /= linies;
			System.out.printf("%.2f", mitjana);
			System.out.print(" ");
			int min = 11;
			int max = 0;
			String nomMin, nomMax;
			nomMin = nomMax = null;
			
			for(String anime2 : mapa.keySet()) {
				if(mapa.get(anime2) > max) {
					max = mapa.get(anime2);
					nomMax = anime2;
				}
				if(mapa.get(anime2) < min) {
					min = mapa.get(anime2);
					nomMin = anime2;
				}
			}
			if(max > min)
				System.out.print(nomMin + " " + nomMax);
			else if (max == min)
				System.out.print(nomMin);
			
		}
	}
}
