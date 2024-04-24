package UF1.Matrius.Desplaçament;

import java.util.Scanner;

public class spiderman2 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int f = ohowo.nextInt();
		int c = ohowo.nextInt();
		String[][] matriu = new String[f][c];
		
		for(int i = 0; i < f; i++) {
			for(int j = 0; j < c; j++) {
				matriu[i][j] = ohowo.next();
			}
		}
		
		for(int i = 0; i < f; i++) {
			for(int j = 0; j < c; j++) {
				String aux = matriu[i][j];
				if(aux.equals("SPIDERMAN") && i > 0 && i < f-1) {
					System.out.println(matriu[i-1][j]);
					System.out.println(matriu[i+1][j]);
					break;
				}
				else if(aux.equals("SPIDERMAN") && i > 0 && i < f) {
					System.out.println(matriu[i-1][j]);
					System.out.println("NO");
					break;
				}
				else if(aux.equals("SPIDERMAN") && i == 0) {
					System.out.println("NO");
					System.out.println(matriu[i+1][j]);
					break;
				}
			}
		}
	}
}
