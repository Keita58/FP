package UF1.Diccionaris.Intro;

import java.util.HashMap;
import java.util.Scanner;

public class valorDiccionari {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			HashMap<String, String> mapa = new HashMap<>();
			HashMap<String, String> mapa2 = new HashMap<>();
			int linies = ohowo.nextInt();
			ohowo.nextLine();
			for(int j = 0; j < linies - 1; j++) {
				String paisCapital = ohowo.nextLine();
				String[] separat = paisCapital.split("-");
				mapa.put(separat[0], separat[1]);
				mapa2.put(separat[1], separat[0]);
			}
			String capital = ohowo.nextLine();
			System.out.println(mapa);
			System.out.println(mapa2.get(capital));
		}
	}
}