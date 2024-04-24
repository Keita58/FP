package UF1.Matrius.Basic;

import java.util.Scanner;

public class matriuTrans {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int f = ohowo.nextInt();
		int c = ohowo.nextInt();
		int[][] matriu = new int[c][f];
		
		for(int i = 0; i < f; i++) {
			for(int j = 0; j < c; j++) {
				matriu[j][i] = ohowo.nextInt();
			}
		}
		for(int i = 0; i < c; i++) {
			for(int j = 0; j < f; j++)
				System.out.print(matriu[i][j]+" ");
			System.out.println();
		}
	}
}
