package UF1.Matrius.Basic;

import java.util.Scanner;

public class eleccionsEscolars {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int num = ohowo.nextInt();
		char[][] matriu = new char[num][num];
		
		for(int i = 0; i < num; i++) 
			for(int j = 0; j < num; j++)
					matriu[i][j] = '.';
			
		for(int i = 0; i < num; i++) {
			for(int j = 0; j < num; j++) {
				if(i == 0 || i == num - 1) 
					matriu[i][j] = 'X';
				else if(j == 0 || j == num - 1)
					matriu[i][j] = 'X';
				else {
					matriu[i][i] = 'X';
					matriu[i][num - 1 - i] = 'X';
				}
			}
		}
		for(int i = 0; i < num; i++) {
			for(int j = 0; j < num; j++)
				System.out.print(matriu[i][j]+" ");
			System.out.println();
		}
	}
}
