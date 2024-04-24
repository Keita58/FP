package ExercicisDificils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Calcular_Binaris {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		ohowo.nextLine();
		
		for(int i = 0; i < casos; i++) {
			int num = ohowo.nextInt();
			String simbol = ohowo.next();
			int num2 = ohowo.nextInt();
			ArrayList<Integer> binari = new ArrayList<>();
			
			if(simbol.equals("+")) {
				int calcul = num+num2;
				while(calcul > 1) {
					binari.add(calcul%2);
					calcul /= 2;
				}
				binari.add(calcul);
				Collections.reverse(binari);
			}
			else {
				int calcul = num-num2;
				while(calcul > 1) {
					binari.add(calcul%2);
					calcul /= 2;
				}
				binari.add(calcul);
				Collections.reverse(binari);
			}
			
			for(int j = 0; j < binari.size(); j++) {
				System.out.print(binari.get(j));
			}
			System.out.println();
		}		
	}
}
