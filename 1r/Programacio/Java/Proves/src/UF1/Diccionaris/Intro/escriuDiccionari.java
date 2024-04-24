package UF1.Diccionaris.Intro;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class escriuDiccionari {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int entrades = ohowo.nextInt();
		ohowo.nextLine();
		LinkedHashMap<String, String> mapa = new LinkedHashMap<>();
		//Amb linked ho afegeix en cascada, no random com ho faria el hash
		
		for(int i = 0; i < entrades; i++) {
			String nom = ohowo.nextLine();
			String data = ohowo.nextLine();
			mapa.put(nom, data);
		}
		String nom = ohowo.nextLine();
		System.out.println(mapa);
		System.out.println(mapa.get(nom));
	}
}
