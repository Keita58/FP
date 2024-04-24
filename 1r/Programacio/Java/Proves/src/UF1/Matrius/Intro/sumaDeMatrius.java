package UF1.Matrius.Intro;

import java.util.Scanner;

public class sumaDeMatrius {
	
	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int linies = ohowo.nextInt();
		int[][] matrix1 = new int[linies][linies];
		
		for(int i = 0; i < linies; i++) {
			for(int j = 0; j < linies; j++) {
				matrix1[i][j] = ohowo.nextInt();
			}
		}
		for(int i = 0; i < linies; i++) {
			for(int j = 0; j < linies; j++) {
				matrix1[i][j] += ohowo.nextInt();
				System.out.print(matrix1[i][j]+" ");
			}
			System.out.println();
		}	
	}
}
