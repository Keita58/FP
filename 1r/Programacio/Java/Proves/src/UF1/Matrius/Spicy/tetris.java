package UF1.Matrius.Spicy;

import java.util.Scanner;

public class tetris {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int f = ohowo.nextInt();
		int c = ohowo.nextInt();
		int[][] matriu = new int[f][c];
		
		for(int i = 0; i < f; i++) {
			for(int j = 0; j < c; j++) {
				matriu[i][j] = ohowo.nextInt();
			}
		}
		int linia = 0;
		for(int i = 0; i < f; i++) {
			int numNo0 = 0;
			for(int j = 0; j < c; j++) {
				if(matriu[i][j] != 0)
					numNo0++;
			}
			if(numNo0 == c)
				linia++;
		}
		if(linia == 4) 
			System.out.println("TETRIS");
		else
			System.out.println(linia);
	}
}
