package UF1.Matrius.Basic;

import java.util.Scanner;

public class travolta {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int f = ohowo.nextInt();
		int c = ohowo.nextInt();
		int t = ohowo.nextInt();
		int num = 0;
		boolean trobat = false;
		int[][] matriu = new int[f][c];
		
		for(int i = 0; i < f && !trobat; i++) {
			for(int j = 0; j < c; j++) {
				matriu[i][j] = ohowo.nextInt();
				if(j >= 2 && matriu[i][j-2] == matriu[i][j]) {
					trobat = true;
					num = matriu[i][j-1];
				}
			}
		}
		if(trobat)
			System.out.println(num);
		else
			System.out.println("NO");
	}

}
