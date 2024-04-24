package UF1.Diccionaris.OG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class surtido {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int min = 10;
			int galetes = 0;
			int linies = ohowo.nextInt();
			ohowo.nextLine();
			HashMap<String, Integer> mapa = new HashMap<>(); //Mapa original
			LinkedHashMap<String, Integer> mapaCorrecte = new LinkedHashMap<>(); //Mapa ordenar descendentment
			ArrayList<Integer> list = new ArrayList<>(); //Per ordenar el nombre de galetes
			
			for(int j = 0; j < linies; j++) { //Afegim els valors al mapa original
				String mapaBat = ohowo.nextLine();
				if(mapa.containsKey(mapaBat))
					mapa.put(mapaBat, mapa.get(mapaBat)+1);
				else {
					mapa.put(mapaBat, 1);
					galetes++;
				}
			}
			
			for(String clau : mapa.keySet()) {//Afegim a l'array els valors del diccionari
				list.add(mapa.get(clau));
			}
			Collections.sort(list, Collections.reverseOrder()); //Els ordenem en ordre descendent (de més a menys)
			
			for(int num : list) { //Per cada nombre de la llista
				for(String clau : mapa.keySet()) { //Per cada entrada al diccionari
					if(mapa.get(clau).equals(num)) //Si el nombre de la llista es igual al valor de una de les entrades del diccionari
						mapaCorrecte.put(clau, mapa.get(clau)); //Afegim al mapa ordenat l'entrada del diccionari
				}
			}
			//No ens hem de preocupar de valors repetits ja que els afegirà cada vegada que aparegui el seu valor del mapa no ordenat
			
			if(galetes > 3) {
				int[] nums = new int[mapa.size()];
				int count = 0;
				for(String clau : mapaCorrecte.keySet()) {
					nums[count] = mapaCorrecte.get(clau);
					count++;
				}
				int caixes = 0;
				float numero = nums.length;
				float meitat = numero/2;
				
				while(true) {
					int zeros = 0;
					int vegades = 0;
					for(int j = 0; j < nums.length && zeros < meitat && vegades < 3; j++) {
						if(nums[j] > 0) {
							nums[j]--;
							vegades++;
						}
						else
							zeros++;
					}
					if(zeros >= meitat)
						break;
					else
						caixes++;
				}
				System.out.println(caixes);
			}
			else
				System.out.println(0);
		}
	}
}
