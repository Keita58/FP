package UF1.Matrius.Desplaçament;

import java.util.Scanner;

public class discotheque {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int f = ohowo.nextInt();
			int c = ohowo.nextInt();
			int k = ohowo.nextInt();
			boolean separat = false;
			int[][] matriu = new int[f][c];
			
			for(int j = 0; j < f; j++) {
				for(int l = 0; l < c; l++) {
					matriu[j][l] = ohowo.nextInt();
				}
			}
			
			for(int j = 0; j < f && !separat; j++) {
				for(int l = 0; l < c && !separat; l++) {
					if(matriu[j][l] == 1) {
						if(j > 0 && l > 0 && j < f-1 && l < c-1) {
							if(matriu[j-1][l] != 1 && matriu[j][l-1] != 1 && matriu[j][l+1] != 1 && matriu[j+1][l] != 1)
								separat = true;
						}
						else if(j == f-1 && l < c-1 && l > 0) {
							if(matriu[j-1][l] != 1 && matriu[j][l-1] != 1 && matriu[j][l+1] != 1)
								separat = true;
						}
						else if(j == 0 && l < c-1 && l > 0) {
							if(matriu[j][l-1] != 1 && matriu[j][l+1] != 1 && matriu[j+1][l] != 1)
								separat = true;
						}
						else if(l == c-1 && f < c-1 && f > 0) {
							if(matriu[j-1][l] != 1 && matriu[j][l-1] != 1 && matriu[j+1][l] != 1)
								separat = true;
						}
						else if(l == 0 && f < c-1 && f > 0) {
							if(matriu[j-1][l] != 1 && matriu[j][l+1] != 1 && matriu[j+1][l] != 1)
								separat = true; 
						}
						else if(j == 0 && l == 0) {
							if(matriu[j+1][l] != 1 && matriu[j][l+1] != 1)
								separat = true;
						}
						else if(j == 0 && l == c-1) {
							if(matriu[j+1][l] != 1 && matriu[j][l-1] != 1)
								separat = true;
						}
						else if(j == f-1 && l == 0) {
							if(matriu[j-1][l] != 1 && matriu[j][l+1] != 1)
								separat = true;
						}
						else if(j == f-1 && l == c-1) {
							if(matriu[j-1][l] != 1 && matriu[j][l-1] != 1)
								separat = true;
						}
					}
				}
			}
			
			if(!separat) 
				System.out.println("OK");
			else
				System.out.println("DISCOTHEQUE, DISCOTHEQUE");
			
		}
	}

}
