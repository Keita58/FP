package UF1.Exercicis_sense_resoldre;

import java.util.Scanner;

public class gat {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		int f, c;
		int[][] matriu;
		
		for(int i = 0; i < casos; i++) {
			f = ohowo.nextInt();
			c = ohowo.nextInt();
			matriu = new int[f][c];
			
			for(int j = 0; j < f; j++) {
				for(int k = 0; k < c; k++) {
					matriu[j][k] = ohowo.nextInt();
				}
			}
		}
	}

}
