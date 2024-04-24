package UF1.Diccionaris.OG;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class arnau {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int versicles = ohowo.nextInt();
			ohowo.nextLine();
			TreeMap<String, String> mapa = new TreeMap<>();
			
			for(int j = 0; j < versicles; j++) {
				String frase = ohowo.nextLine();
				String[] paraules = frase.split(" ");
				String paraula = "";
				String clau = "";
				int n = 0; 
				boolean numeric = false;
				try {
				    int Value = Integer.parseInt(paraules[2]);
				    numeric = true;
				} catch (NumberFormatException e) {
					clau = paraules[0]+paraules[1];
					n = 2;
				}
				
				if(numeric) {
					clau = paraules[0]+paraules[1]+":"+paraules[2];
				    n = 3;
				}				
				
				for(; n < paraules.length - 1; n++) {
					paraula += paraules[n]+" ";
				}
				paraula += paraules[paraules.length - 1];
				mapa.put(clau, paraula);
			}
			System.out.println(mapa);
			String lletra = ohowo.nextLine();
			
			for(String clau : mapa.keySet()) {
				Pattern p = Pattern.compile(lletra, Pattern.CASE_INSENSITIVE);
			    Matcher m = p.matcher(clau);
			    if(m.find())
			    	System.out.println('"'+mapa.get(clau)+'"');
			}
		}
	}
}
