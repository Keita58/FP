package Examen;

import java.util.HashMap;
import java.util.Scanner;

public class exercici1 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int anotacions = ohowo.nextInt();
		HashMap<String, Integer> mapa = new HashMap<>();
		
		for(int i = 0; i < anotacions; i++) {
			String classe = ohowo.next();
			int nivell = ohowo.nextInt();
			nivell += mapa.getOrDefault(classe, 0);
			mapa.put(classe, nivell);
		}
		for(String clau : mapa.keySet()) {
			if(mapa.get(clau) > 0)
				System.out.println(clau);
		}
	}
}
