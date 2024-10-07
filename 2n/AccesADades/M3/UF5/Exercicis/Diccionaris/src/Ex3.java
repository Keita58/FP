import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class Ex3 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			//Map<String, String> valoracio = new HashMap<>();
			SortedMap<String, String> valoracio = new TreeMap<>();
			int linies = ohowo.nextInt();
			ohowo.nextLine();
			float mitjana = 0;
			
			for(int j = 0; j < linies; j++) {
				String anime = ohowo.nextLine();
				String[] separa = anime.split("-");
				mitjana += Integer.parseInt(separa[1]);
				valoracio.put(separa[0], separa[1]);
			}	
			mitjana = mitjana/linies;
			System.out.printf("%.2f", mitjana);
			
			int min = 11;
			for(String clau : valoracio.keySet()) {
				if(Integer.parseInt(valoracio.get(clau)) < min) {
					min = Integer.parseInt(valoracio.get(clau));
				}
			}
			
			for(Map.Entry<String, String> res : valoracio.entrySet()) {
				if(Integer.parseInt(res.getValue()) == min) {
					System.out.print(" " + res.getKey());
					break;
				}
			}
			
			int max = 0;
			for(String clau : valoracio.keySet()) {
				if(Integer.parseInt(valoracio.get(clau)) > max) {
					max = Integer.parseInt(valoracio.get(clau));
				}
			}

			for(Map.Entry<String, String> res : valoracio.entrySet()) {
				if(Integer.parseInt(res.getValue()) == max) {
					System.out.print(" " + res.getKey());
					break;
				}
			}
		}
	}
}
