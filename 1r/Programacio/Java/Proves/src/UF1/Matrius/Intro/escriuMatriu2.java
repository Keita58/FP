package UF1.Matrius.Intro;
import java.util.Scanner;

public class escriuMatriu2 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		//int linies = scan.nextInt();
		int f = ohowo.nextInt();
		int c = ohowo.nextInt();
		int[][] matrix = new int[f][c];
		
		for(int i = 0; i < f; i++) {
			for(int j = 0; j < c; j++) {
				matrix[i][j] = ohowo.nextInt();
			}	
		}
		int i = ohowo.nextInt();
		int j = ohowo.nextInt();
		
		for(int k = 0; k < f; k++) {
			for(int l = 0; l < c; l++) {
				if(matrix[k][l] == i)
					System.out.print(j+" ");
				else
					System.out.print(matrix[k][l]+" ");
			}
			System.out.println();
		}
	}
}