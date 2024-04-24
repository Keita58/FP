package UF1.Matrius.Intro;

import java.util.Scanner;

public class sumaFilesColumnes {

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
		int k = ohowo.nextInt();
		int sumf = 0;
		int sumc = 0;
		
		for(int i = 0; i < f; i++) {
			sumc += matriu[i][k];
		}
		for(int i = 0; i < c; i++) {
			sumf += matriu[k][i];
		}
		System.out.println(sumf+" "+sumc);
	}
}