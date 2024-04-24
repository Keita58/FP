package UF1.Matrius.Desplaçament;

import java.util.Scanner;

public class fantasmes {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int f = ohowo.nextInt();
			int c = ohowo.nextInt();
			int k = ohowo.nextInt();
			int posXBlau = ohowo.nextInt();
			int posYBlau = ohowo.nextInt();
			int[][] matriu = new int[f][c];
			
			for(int j = 0; j < f; j++) {
				for(int a = 0; a < c; a++) {
					if(j == 0 || j == f-1)
						matriu[j][a] = 1;
					else if(a == 0 || a == c-1)
						matriu[j][a] = 1;
				}
			}
			
			for(int a = 0; a < k-1; a++) 
				matriu[ohowo.nextInt()][ohowo.nextInt()] = 1;
			
			
			if(matriu[posXBlau-1][posYBlau] == 1 && matriu[posXBlau][posYBlau-1] == 1 && matriu[posXBlau][posYBlau+1] == 1 && matriu[posXBlau+1][posYBlau] == 1)
				System.out.println("OH NO");
			else
				System.out.println("THIS IS FINE");
		}
	}

}
