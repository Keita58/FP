package UF1.Matrius.Basic;

import java.util.Scanner;

public class rectangle {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int f = ohowo.nextInt();
		int c = ohowo.nextInt();
		int f1 = ohowo.nextInt();
		int c1 = ohowo.nextInt();
		int f2 = ohowo.nextInt();
		int c2 = ohowo.nextInt();
		int[][] matriu = new int[f][c];
		
		for(int i = 0; i < f; i++) {
			for(int j = 0; j < c; j++) {
				if(i >= f1 && i <= f2 && j >=c1 && j <= c2)
					System.out.print("X"+" ");
				else
					System.out.print("."+" ");
			}
			System.out.println();
		}
	}

}
