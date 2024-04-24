package ExercicisDificils;

import java.util.Scanner;

public class oftalmoleg {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		StringBuilder canvisPre = new StringBuilder(ohowo.nextLine());
		int[][] matriu = { {1, 2}, {3, 4} };
		int h, v, aux1, aux2;
		h = v = aux1 = aux2 = 0;
		
		for(int i = 0; i < canvisPre.length(); i++) 
			if(canvisPre.charAt(i) == 'H')
				h++;
			else
				v++;
		
		if(h%2 == 1) {
			aux1 = matriu[0][0];
			aux2 = matriu[0][1];
			matriu[0][0] = matriu[1][0];
			matriu[0][1] = matriu[1][1];
			matriu[1][0] = aux1;
			matriu[1][1] = aux2;
		}
		
		if(v%2 == 1) {
			aux1 = matriu[0][0];
			aux2 = matriu[1][0];
			matriu[0][0] = matriu[0][1];
			matriu[1][0] = matriu[1][1];
			matriu[0][1] = aux1;
			matriu[1][1] = aux2;
		}
		
		System.out.print(matriu[0][0]+" "+matriu[0][1]);
		System.out.println();
		System.out.print(matriu[1][0]+" "+matriu[1][1]);
	}
}
