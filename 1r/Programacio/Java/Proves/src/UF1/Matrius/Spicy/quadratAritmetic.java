package UF1.Matrius.Spicy;

import java.util.Scanner;

public class quadratAritmetic {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int[][] matriu = new int[3][3];
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				matriu[i][j] = ohowo.nextInt();
			}
		}
		int a = 0;
		int d = 0;
		if(matriu[0][1] - matriu[0][0] == matriu[0][2] - matriu[0][1]) {
			a = matriu[0][1] - matriu[0][0];
			if(matriu[1][1] - matriu[1][0] == matriu[1][2] - matriu[1][1]) {
				d = (matriu[1][1] - matriu[1][0]) - a;
				if(matriu[2][1] - matriu[2][0] == matriu[2][2] - matriu[2][1] && matriu[2][1] - matriu[2][0] == a+2*d)
					System.out.println("si");
				else 
					System.out.println("no");
			}
			else 
				System.out.println("no");
		}
		else 
			System.out.println("no");
	}
}
