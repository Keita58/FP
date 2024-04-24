package UF1.Matrius.Spicy;

import java.util.Collections;
import java.util.Scanner;

public class quadratMagic {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int k = 0; k < casos; k++) {
			int[][] matriu = new int[3][3];
			boolean correcte = true;
			for(int j = 0; j < 3; j++) {
				for(int f = 0; f < 3; f++) {
					matriu[j][f] = ohowo.nextInt();
				}
			}
			
			for(int l = 0; l < 3; l++) {
				for(int i = 0; i < 3; i++) {
					for(int j = i+1; j < 3; j++) {
						if(matriu[l][i] == matriu[l][j]) {
							correcte = false;
							break;
						}
					}
				}
				for(int i = 0; i < 3; i++) {
					for(int j = i+1; j < 3; j++) {
						if(matriu[i][l] == matriu[j][l]) {
							correcte = false;
							break;
						}
					}
				}
			}
			
			for(int i = 0; i < 3 && correcte; i++) {
				int sumF = 0;
				int sumC = 0;
				for(int j = 0; j < 3; j++) {
					sumF += matriu[i][j];
					sumC += matriu[j][i];
				}
				
				if(sumF > 15 || sumF < 15) 
					correcte = false;
				else if(sumC > 15 || sumF < 15) 
					correcte = false;
			}
			
			if(correcte) {
				if((matriu[0][0] + matriu[1][1] + matriu[2][2]) > 15 || (matriu[0][0] + matriu[1][1] + matriu[2][2]) < 15)
					System.out.println("NO");
				else
					System.out.println("SI");
			}
			else
				System.out.println("NO");
		}
	}
}
