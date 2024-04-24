package UF1.Matrius.Basic;

import java.util.Scanner;

public class blackFriday {

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
		int mult = ohowo.nextInt();
		for(int i = 0; i < f; i++) {
			for(int j = 0; j < c; j++) {
				System.out.print(matriu[i][j]*mult+" ");
			}
			System.out.println();
		}
	}
}