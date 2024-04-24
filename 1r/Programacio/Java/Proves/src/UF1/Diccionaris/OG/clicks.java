package UF1.Diccionaris.OG;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class clicks {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			boolean seguent = false;
			LinkedHashMap<String, String> mapa = new LinkedHashMap<>();
			LinkedHashMap<String, String> mapa2 = new LinkedHashMap<>();
			int linies = ohowo.nextInt();
			ohowo.nextLine();
			
			for(int j = 0; j < linies; j++) {
				String pagines = ohowo.nextLine();
				String[] dividit = pagines.split("->");
				
				if(dividit[1].equals("HITLER") && !seguent) {
					seguent = true;
					mapa.put(dividit[0], dividit[1]);
				}
				else if(seguent) 
					mapa2.put(dividit[0], dividit[1]);
				else
					mapa.put(dividit[0], dividit[1]);
			}
			String inici = ohowo.nextLine();
			int numMap = 0;
			int numMap2 = 0;
			
			if(!inici.equals("HITLER")) {
				int count = 0;
				String clau = mapa.keySet().iterator().next();
				for(String claus : mapa.keySet()) {
					if(mapa.get(clau) != null) { 
						clau = mapa.get(clau);
					}
					if(clau.equals("HITLER")) {
						numMap = count+1;
						break;
					}
					count++;
				}
				
				count = 0;
				if(!mapa2.isEmpty()) {
					clau = mapa2.keySet().iterator().next();
					for(String claus : mapa2.keySet()) {
						if(mapa2.get(clau) != null) { 
							clau = mapa2.get(clau);
						}
						if(clau.equals("HITLER")) {
							numMap2 = count+1;
							break;
						}
						count++;
					}
				}
				if(numMap == 0 && numMap2 == 0)
					System.out.println("IMPOSSIBLE");
				else {
					if(numMap > numMap2 && !mapa2.isEmpty())
						System.out.println(numMap2);
					else
						System.out.println(numMap);
				}
			}
			else
				System.out.println(0);
		}
	}
}