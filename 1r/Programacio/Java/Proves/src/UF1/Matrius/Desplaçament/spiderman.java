package UF1.Matrius.Desplaçament;

import java.util.Scanner;

public class spiderman {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int f = ohowo.nextInt();
		int c = ohowo.nextInt();
		String[][] matriu = new String[f][c];
		
		for(int i = 0; i < f; i++) {
			for(int j = 0; j < c; j++) {
				String aux = ohowo.next();
				if(aux.equals("SPIDERMAN") && i > 0) {
					System.out.println(matriu[i-1][j]);
					break;
				}
				else if(aux.equals("SPIDERMAN")) {
					System.out.println("NO");
				}
				matriu[i][j] = aux;
			}
		}
	}
}
