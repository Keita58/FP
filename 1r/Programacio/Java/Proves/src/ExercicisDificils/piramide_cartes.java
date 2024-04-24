package ExercicisDificils;
import java.util.Scanner;

public class piramide_cartes {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int cartes = ohowo.nextInt();
			int total = cartes;
			int pisos = 0;
			int aux = 1;
			int aux2 = 0;
			
			while(true) {
				if(total - (2*aux+aux2) >= 0) {
					pisos++;
					total -= (2*aux+aux2);
				}
				else
					break;
				aux++;
				aux2++;
			}
			System.out.println(pisos+" "+total);
		}
	}
}
