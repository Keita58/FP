package UF1.Matrius.Intro;
import java.util.Scanner;

public class index {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int f = ohowo.nextInt();
		int c = ohowo.nextInt();
		int[][] matrix = new int[f][c];
		
		for(int i = 0; i < f; i++) {
			for(int j = 0; j < c; j++) {
				matrix[i][j] = ohowo.nextInt();
			}
		}
		int num = ohowo.nextInt();
		int pos1, pos2;
		pos1 = pos2 = -1;
		for(int i = 0; i < f; i++) {
			for(int j = 0; j < c; j++) {
				if(matrix[i][j] == num) {
					pos1 = i;
					pos2 = j;
				}
			}
		}
		System.out.println(pos1+" "+pos2);
	}

}
