package UF1.Matrius.Spicy;

import java.util.Scanner;

public class jocDeLaVida {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int f = ohowo.nextInt();
		int c = ohowo.nextInt();
		ohowo.nextLine();
		int[][] matriu = new int[f][c];
		int[][] matriuFinal = new int[f][c];
		
		for(int i = 0; i < f; i++) {
			String num = ohowo.nextLine();
			String[] nums = num.split("");
			for(int j = 0; j < c; j++) {
				matriu[i][j] = Integer.parseInt(nums[j]);
				matriuFinal[i][j] = Integer.parseInt(nums[j]);
			}
		}
		
		for(int i = 0; i < f; i++) {
			for(int j = 0; j < c; j++) {
				int num = 0;
				if(i > 0 && j > 0 && matriu[i-1][j-1] == 1)
					num++;
				if(i > 0 && matriu[i-1][j] == 1)
					num++;
				if(i > 0 && j < c-1 && matriu[i-1][j+1] == 1)
					num++;
				if(j > 0 && matriu[i][j-1] == 1)
					num++;
				if(j < c-1 && matriu[i][j+1] == 1)
					num++;
				if(j > 0 && i < f-1 && matriu[i+1][j-1] == 1)
					num++;
				if(i < f-1 && matriu[i+1][j] == 1)
					num++;
				if(j < c-1 && i < f-1 && matriu[i+1][j+1] == 1)
					num++;

				if(num > 3 || num < 2)
					matriuFinal[i][j] = 0;
				else if(num <= 3 || num >= 2)
					matriuFinal[i][j] = 1;
			}
		}
		
		for(int i = 0; i < f; i++) {
			for(int j = 0; j < c; j++) {
				System.out.print(matriuFinal[i][j]);
			}
			System.out.println();
		}
	}
}
