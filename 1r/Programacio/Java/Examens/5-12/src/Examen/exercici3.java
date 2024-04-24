package Examen;

import java.util.Scanner;

public class exercici3 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int n = ohowo.nextInt();
		int m = ohowo.nextInt();
		int[][] matriu = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				matriu[i][j] = ohowo.nextInt();
			}
		}
		
		int posX = ohowo.nextInt();
		int posY = ohowo.nextInt();
		
		if(matriu[posX][posY] == 1) {
			int count = 0;
			if(posX > 0 && posY > 0 && matriu[posX - 1][posY - 1] == 1)
				count++;
			if(posX > 0 && posY < m - 1 && matriu[posX - 1][posY + 1] == 1)
				count++;
			if(posX < n - 1 && posY > 0 && matriu[posX + 1][posY - 1] == 1)
				count++;
			if(posX < n - 1 && posY < m - 1 && matriu[posX + 1][posY + 1] == 1)
				count++;
			
			if(count == 4)
				System.out.println("X");
			else if(count > 1)
				System.out.println("PARTIAL X");
			else
				System.out.println("ALONE");
		}
		else
			System.out.println("DOWN");
	}
}
