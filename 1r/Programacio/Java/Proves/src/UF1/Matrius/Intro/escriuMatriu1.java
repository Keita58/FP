package UF1.Matrius.Intro;
import java.util.Scanner;

public class escriuMatriu1 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		//int linies = scan.nextInt();
		int f = ohowo.nextInt();
		int c = ohowo.nextInt();
		int[][] matrix = new int[f][c];
		
		for(int i = 0; i < f; i++) {
			for(int j = 0; j < c; j++) {
				matrix[i][j] = ohowo.nextInt();
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println(matrix[ohowo.nextInt()][ohowo.nextInt()]);
	}

}
