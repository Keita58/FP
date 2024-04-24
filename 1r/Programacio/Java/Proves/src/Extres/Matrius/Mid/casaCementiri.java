package Extres.Matrius.Mid;

import java.util.Scanner;

public class casaCementiri {

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
		int fila = ohowo.nextInt();
		int columna = ohowo.nextInt();
		
		if(fila > 0 && columna > 0 && columna < c-1 && fila < f-1 && (matriu[fila-1][columna] == 4 || matriu[fila][columna - 1] == 4 || matriu[fila][columna + 1] == 4 || matriu[fila + 1][columna] == 4))
			System.out.println("NO");
		else if(fila < f - 1 && columna < c - 1 && (matriu[fila][columna + 1] == 4 || matriu[fila + 1][columna] == 4))
			System.out.println("NO");
		else if(fila < f - 1 && columna > 0 && (matriu[fila][columna - 1] == 4 || matriu[fila + 1][columna] == 4))
			System.out.println("NO");
		else if(fila > 0 && columna < c - 1 && (matriu[fila][columna + 1] == 4 || matriu[fila - 1][columna] == 4))
			System.out.println("NO");
		else if(fila > 0 && columna > 0 && (matriu[fila][columna - 1] == 4 || matriu[fila - 1][columna] == 4))
			System.out.println("NO");
		else
			System.out.println("SI");
	}

}
