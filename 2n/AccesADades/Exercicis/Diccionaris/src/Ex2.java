import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ex2 {
	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int linies = ohowo.nextInt();
			ohowo.nextLine();
			Map<String, Integer> noms = new HashMap<>();
			
			for(int j = 0; j < linies; j++) {
				String guanyador = ohowo.nextLine();
				String[] separa = guanyador.split("-");
				if(!noms.containsKey(separa[1]))
					noms.put(separa[1], 1);
				else
					noms.put(separa[1], noms.get(separa[1])+1);
			}
			
			
			int mida = noms.size();
			
			for(int j = 0; j < mida; j++) {	
				int max = 0;
				for(String clau : noms.keySet()) {
					if(noms.get(clau) > max)
						max = noms.get(clau);
				}
				for(Map.Entry<String, Integer> res : noms.entrySet()) {
					if(res.getValue() == max) {
						System.out.println(res.getKey()+"-"+res.getValue());
						noms.remove(res.getKey());
						break;
					}
				}
			}
		}
	}
}
