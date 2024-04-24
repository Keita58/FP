package Examen;

import java.util.Scanner;

public class exercici2 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int x = ohowo.nextInt();
		int y = ohowo.nextInt();
		char[][] matriu = new char[x][y];
			
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				if(i == x/2)
					matriu[i][j] = 'X';
				else if(j == (y/3)) {
					matriu[i][j] = 'X';
				}
				else
					matriu[i][j] = '.';
			}
		}
		
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++)
				System.out.print(matriu[i][j]+" ");
			System.out.println();
		}
	}
}
