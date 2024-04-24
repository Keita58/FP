package UF1.Diccionaris.Intro;

import java.util.HashMap;
import java.util.Scanner;

public class desert {

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
				String mapaBat = ohowo.nextLine();
				if(mapa.containsKey(mapaBat))
					mapa.put(mapaBat, mapa.get(mapaBat)+1);
				else
					mapa.put(mapaBat, 1);
				if(mapa.get(mapaBat) > max) {
					max = mapa.get(mapaBat);
					maxMapa = mapaBat;
				}
			}
			System.out.println(maxMapa);
		}
	}
}
