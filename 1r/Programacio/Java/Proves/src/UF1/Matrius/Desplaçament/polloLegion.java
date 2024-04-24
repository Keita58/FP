package UF1.Matrius.Desplaçament;

import java.util.Scanner;

public class polloLegion {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int f = ohowo.nextInt();
			int c = ohowo.nextInt();
			int posX, posY, enemics;
			enemics = posX = posY = 0;
			int[][] matriu = new int[f][c];
			
			for(int j = 0; j < f; j++) {
				for(int k = 0; k < c; k++) {
					int croux = ohowo.nextInt();
					if(croux == 2) {
						posX = j;
						posY = k;
					}
					matriu[j][k] = croux;
				}
			}
			int numX = 0;
			int numY = 0;
			if(posX-2 < 0) 
				numX = 0;
			else
				numX = posX-2;
			if(posY-2 < 0) 
				numY = 0;
			else
				numY = posY-2;
			
			for(int j = numX; j <= posX+2 && j < f && j >= 0; j++) {
				for(int k = numY; k <= posY+2 && k < c && k >= 0; k++) {
					if(matriu[j][k] == 1)
						enemics++;
				}
			}
			
			if(enemics > 3)
				System.out.println("SI");
			else
				System.out.println("NO");
		}
	}
}
