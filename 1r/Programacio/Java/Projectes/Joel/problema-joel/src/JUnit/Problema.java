package JUnit;

import java.util.Arrays;
import java.util.Scanner;


/**
 * @author Marc Sánchez(Keita58)
 * @version 1.0 (current)
 */
public class Problema {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		
		int f = ohowo.nextInt();
		int[][] matriu = new int[f][f];
		
		for(int i = 0; i < f; i++) {
			for(int j = 0; j < f; j++) {
				matriu[i][j] = ohowo.nextInt();
			}
		}
		
		int[][] res = posicio(matriu);	
		System.out.println(Arrays.deepToString(res));
	}
	
	/**
	 * Processem la matriu per trobar la cantonada més il·luminada
	 * 
	 * <p>
	 * Sumem les quatre cantonades de la matriu (de mida N-1*N-1) i comprovem quina d'aquestes és la suma més gran.
	 * Per evitar uns temps de càlcul molt elevats, només mirem les files 0 i N-1 i les columnes 0 i N-1 ja que la resta de la matriu
	 * és igual per a les quatre cantonades.
	 * </p>
	 * 
	 * @param matriu la matriu original que parsegem
	 * @return matriu amb les coordenades de la matriu N-1*N-1
	 */
	public static int[][] posicio(int[][] matriu) {
		
		int q1, q2, q3, q4;
		q1 = q2 = q3 = q4 = 0;
		
		for(int i = 0; i < matriu.length; i++) {
			if(i == 0) {
				for(int j = 0; j < matriu.length; j++) {
					if(j == 0)
						q1 += matriu[i][j];
					else if(j == matriu.length - 1)
						q2 += matriu[i][j];
					else {
						q1 += matriu[i][j];
						q2 += matriu[i][j];
					}
				}
			}
			else if (i == matriu.length - 1) {
				for(int j = 0; j < matriu.length; j++) {
					if(j == 0)
						q3 += matriu[i][j];
					else if(j == matriu.length - 1)
						q4 += matriu[i][j];
					else {
						q3 += matriu[i][j];
						q4 += matriu[i][j];
					}
				}
			}
			else {
				q1 += matriu[i][0];
				q3 += matriu[i][0];
				q2 += matriu[i][matriu.length - 1];
				q4 += matriu[i][matriu.length - 1];
			}
		}
		
		if(q1 > q2 && q1 > q3 && q1 > q4) {
			int[][] solucio = {{0,0}, {matriu.length - 2, matriu.length - 2}};
			return solucio;
		}
		else if(q1 < q2 && q2 > q3 && q2 > q4) {
			int[][] solucio = {{0,1}, {matriu.length - 2, matriu.length - 1}};
			return solucio;
		}
		else if(q3 > q1 && q3 > q2 && q3 > q4) {
			int[][] solucio = {{1,0}, {matriu.length - 1, matriu.length - 2}};
			return solucio;
		}
		else if(q4 > q1 && q4 > q2 && q3 < q4) {
			int[][] solucio = {{1,1}, {matriu.length - 1, matriu.length - 1}};
			return solucio;
		}
		// En aquests primers if else mirem si les quatre coordenades son diferents, si no ho son passem a l'else
		else {
			if(q1 == q2) {
				if(matriu.length > 1) {
					int[][] solucio = {{0,0}, {matriu.length - 2, matriu.length - 2}};
					return solucio;
				}
				else {
					int[][] solucio = {{0,0}, {0,0}};
					return solucio;
				}
			}
			else if(q1 == q3) {
				int[][] solucio = {{0,0}, {matriu.length - 2, matriu.length - 2}};
				return solucio;
			}
			else if(q1 == q4) {
				int[][] solucio = {{0,0}, {matriu.length - 2, matriu.length - 2}};
				return solucio;
			}
			else if(q2 == q3) {
				int[][] solucio = {{0,1}, {matriu.length - 2, matriu.length - 1}};
				return solucio;
			}
			else if(q2 == q4) {
				int[][] solucio = {{0,1}, {matriu.length - 2, matriu.length - 1}};
				return solucio;
			}
			else if(q3 == q4) {
				int[][] solucio = {{1,0}, {matriu.length - 1, matriu.length - 2}};
				return solucio;
			}
			// En aquests if else mirem si hi ha cap coordenada igual, només 1 a 1
			else {
				if(q1 == q2 && q1 == q3) {
					int[][] solucio = {{0,0}, {matriu.length - 2, matriu.length - 2}};
					return solucio;
				}
				else if(q1 == q2 && q1 == q4) {
					int[][] solucio = {{0,0}, {matriu.length - 2, matriu.length - 2}};
					return solucio;
				}
				else if(q1 == q4 && q1 == q3) {
					int[][] solucio = {{0,0}, {matriu.length - 2, matriu.length - 2}};
					return solucio;
				}
				else if(q2 == q3 && q2 == q4) {
					int[][] solucio = {{0,1}, {matriu.length - 2, matriu.length - 1}};
					return solucio;
				}
				else {
					int[][] solucio = {{0,0}, {matriu.length - 2, matriu.length - 2}};
					return solucio;
				}
				// I en aquests if else mirem si hi ha tres coordenades iguals i l'últim cas, si totes quatre son iguals
			}
		}
	}
}
