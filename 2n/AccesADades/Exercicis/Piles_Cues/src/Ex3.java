import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Ex3 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		LinkedHashMap<String, ArrayList<String>> treballs = new LinkedHashMap<>();
		
		for(int i = 0; i < casos; i++) {
			int numCasos = ohowo.nextInt();
			ohowo.nextLine();
			
			for(int j = 0; j < numCasos; j++) {
				String[] treball = ohowo.nextLine().split("-");
				ArrayList<String> dep2 = new ArrayList<>();
				if(treball.length > 1) {
					String[] dep = treball[1].split(",");
				
					for(int k = 0; k < dep.length; k++)
						dep2.add(dep[k]);
					treballs.put(treball[0], dep2);
				}
				else
					treballs.put(treball[0], null);
			}
			
			LinkedHashMap<String, ArrayList<String>> aux = new LinkedHashMap<>();
			
			for(String clau : treballs.keySet()) {
				if(treballs.get(clau) == null) {
					ArrayList<String> aux2 = treballs.remove(clau);
					aux.put(clau, aux2);
					aux.putAll(treballs);
					break;
				}
			}
			treballs.clear();
			
			int max = 0;
			String clauMax = "";
			for(String clau : aux.keySet()) {
				if(aux.get(clau) != null && aux.get(clau).size() > max) {
					max = aux.get(clau).size();
					clauMax = clau;
				}
			}
			ArrayList<String> aux2 = aux.remove(clauMax);
			treballs.putAll(aux);
			treballs.put(clauMax, aux2);
			
			String primeraClau = treballs.firstEntry().getKey();
			for(String clau : treballs.keySet()) {
				if(treballs.get(clau) != null && treballs.get(primeraClau).equals(primeraClau)) {
					
				}
			}
		}
	}
}
