package UF2.Recursivitat;

import java.util.Scanner;

public class Ambrosio_Triangular {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int num = ohowo.nextInt();
			int totalBombons = 0;
			int total = 0;
			int aux = 1;
			total = rec(totalBombons, total, num, aux);
			
			System.out.println(total);
		}	
	}
	
	public static int rec(int totalBombons, int total, int num, int aux) {
		if(num + 1 == aux)
			return total;
		else {
			totalBombons = totalBombons + aux;
			total = total + totalBombons;
			aux++;
			total = rec(totalBombons, total, num, aux);
		}
		return total;
	}
}
