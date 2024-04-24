package UF1.Contar;
import java.util.Scanner;

public class contar_quadrats {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int m = ohowo.nextInt();
			int n = ohowo.nextInt();
			int quadrats = 0; 
			
			for(int k = 0; k < Math.min(m, n); k++) {
				quadrats += (m-k)*(n-k);
			}
			System.out.println(quadrats);
		}
	}
}
