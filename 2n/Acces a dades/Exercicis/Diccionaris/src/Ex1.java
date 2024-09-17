import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ex1 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int linies = ohowo.nextInt();
			ohowo.nextLine();
			Map<String, String> guanyadors = new HashMap<>();
			
			for(int j = 0; j < linies - 1; j++) {
				String guanyador = ohowo.nextLine();
				String[] separa = guanyador.split("-");
				guanyadors.put(separa[0], separa[1]);
			}
			
			String alias = ohowo.nextLine();
			int vegades = 0;
			for(String nom : guanyadors.keySet()) {
				if(guanyadors.get(nom).equals(alias))
					vegades++;
			}
			System.out.println(vegades);
		}
	}
}
