package UF1.Matrius.Basic;

import java.util.Scanner;

public class matriuIdentitat {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int num = ohowo.nextInt();
		int[][] matriu = new int[num][num];
		
		for(int i = 0; i < num; i++) {
			for(int j = 0; j < num; j++) {
				matriu[i][j] = 0;
				matriu[i][i] = 1;
			}
		}
		for(int i = 0; i < num; i++) { 
			for(int j = 0; j < num; j++)
				System.out.print(matriu[i][j]+" ");
			System.out.println();
		}
	}
}
