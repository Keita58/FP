package UF1.Matrius.Intro;

import java.util.Scanner;

public class Notes1 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int f = ohowo.nextInt();
			int c = ohowo.nextInt();
			int matrix[][] = new int[f][c];
			
			for(int j = 0; j < f; j++) {
				int mitjana = 0;
				for(int k = 0; k < c; k++) {
					matrix[j][k] = ohowo.nextInt();
					mitjana += matrix[j][k];
				}
				System.out.print((mitjana/c)+" ");
			}
			System.out.println();
		}
	}
}
