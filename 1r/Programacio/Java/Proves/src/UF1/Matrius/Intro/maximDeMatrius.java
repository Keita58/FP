package UF1.Matrius.Intro;

import java.util.Scanner;

public class maximDeMatrius {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int f = ohowo.nextInt();
			int c = ohowo.nextInt();
			int max = 0;
			int pos1, pos2;
			pos1 = pos2 = 0;
			int[][] matrix1 = new int[f][c];
			for(int j = 0; j < f; j++) {
				for(int k = 0; k < c; k++) {
					matrix1[j][k] = ohowo.nextInt();
					if(matrix1[j][k] > max) {
						max = matrix1[j][k];
						pos1 = j+1;
						pos2 = k+1;
					}
				}
			}
			System.out.print(pos1+" "+pos2);
			System.out.println();
		}
	}
}
