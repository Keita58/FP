package UF1.Diccionaris.Intro;

import java.util.HashMap;
import java.util.Scanner;

public class salman {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		HashMap<String, String> mapa = new HashMap<>();
		
		for(int i = 0; i < casos; i++) {
			int linies = ohowo.nextInt();
			ohowo.nextLine();
			for(int j = 0; j < linies - 1; j++) {
				String amics = ohowo.nextLine();
				String[] amic = amics.split(" ");
				mapa.put(amic[0], amic[1]);
				mapa.put(amic[1], amic[0]);
			}
			String nom = ohowo.nextLine();
			System.out.println(mapa.get(nom));
		}
	}
}
