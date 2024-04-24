package UF1.Exercicis_sense_resoldre;

import java.util.Scanner;

public class athena {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int f = ohowo.nextInt();
			int c = ohowo.nextInt();
			int[][] matriu = new int[f][c];
			
			for(int j = 0; j < f; j++) {
				for(int k = 0; k < c; k++) {
					matriu[j][k] = ohowo.nextInt();
				}
			}
			int coordX = ohowo.nextInt();
			int coordY = ohowo.nextInt();
			int mines = 0;
			
			if(matriu[coordX][coordY] == 1 && f > 1 && c > 1) {
				if(coordX > 0 && coordY > 0 && matriu[coordX-1][coordY-1] == 2)
					mines++;
				if(coordX > 0 && matriu[coordX-1][coordY] == 2)
					mines++;
				if(coordX > 0 && coordY < c-1 && matriu[coordX-1][coordY+1] == 2)
					mines++;
				if(coordY > 0 && matriu[coordX][coordY-1] == 2)
					mines++;
				if(coordY < c-1 && matriu[coordX][coordY+1] == 2)
					mines++;
				if(coordY > 0 && coordX < f-1 && matriu[coordX+1][coordY-1] == 2)
					mines++;
				if(coordX < f-1 && matriu[coordX+1][coordY] == 2)
					mines++;
				if(coordY < c-1 && coordX < f-1 && matriu[coordX+1][coordY+1] == 2)
					mines++;
				System.out.println(mines);
			}
			else
				System.out.println(-1);				
		}
	}
}
