package UF1.Diccionaris.Intro;

import java.util.HashMap;
import java.util.Scanner;

public class capitals {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int països = ohowo.nextInt();
			ohowo.nextLine();
			HashMap<String, String> mapa = new HashMap<>();
			for(int j = 0; j < països - 1; j++) {
				String link = ohowo.nextLine();
				String[] links = link.split("-");
				mapa.put(links[0], links[1]);
			}
			String pais = ohowo.nextLine();
			if(mapa.containsKey(pais))
				System.out.println(mapa.get(pais));
			else
				System.out.println("NO HO SE");
		}
	}
}
