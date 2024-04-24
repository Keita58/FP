package UF1.Matrius.Spicy;

import java.util.Scanner;

public class bomberman {

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
		int coordX = ohowo.nextInt();
		int coordY = ohowo.nextInt();
		int sum = 0;
		
		for(int i = 0; i < f; i++) {
			for(int j = 0; j < c; j++) {
				if(j == coordY && (i < coordX || i > coordX)) 
					sum += matriu[i][j];
				else if(i == coordX && (j < coordY || j > coordY))
					sum += matriu[i][j];
				else if(i == coordX && j == coordY)
					sum += matriu[i][j];
			}
		}
		System.out.println(sum);
	}
}
