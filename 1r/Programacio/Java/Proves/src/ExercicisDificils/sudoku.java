package ExercicisDificils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class sudoku {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int N = ohowo.nextInt();
		
		for(int k = 0; k < N; k++) {
			int[][] matriu = new int[9][9];
			boolean correcte = true;
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					matriu[i][j] = ohowo.nextInt();
				}
			}
			for(int l = 0; l < 9; l++) {
				for(int i = 0; i < 9; i++) {
					for(int j = i+1; j < 9; j++) {
						if(matriu[l][i] == matriu[l][j]) {
							correcte = false;
							break;
						}
					}
				}
				for(int i = 0; i < 9; i++) {
					for(int j = i+1; j < 9; j++) {
						if(matriu[i][l] == matriu[j][l]) {
							correcte = false;
							break;
						}
					}
				}
			}
			System.out.println(correcte);
		}
	}
}
