package RepasExamens.UF1;

import java.util.HashMap;
import java.util.Scanner;

public class repasExamen30_11 {
	static void exercici1(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int linies = ohowo.nextInt();
		HashMap<Integer, Integer> mapa = new HashMap<>();
		boolean error = false;
		int questRepetibles = 0;
		int questNoRepetibles = 0;
		
		for(int i = 0; i < linies; i++) {
			int id = ohowo.nextInt();
			int estat = ohowo.nextInt();
			if(mapa.get(id) != null) {
				if(id > 999) {
					if(mapa.get(id) == 3 && estat == 1) {
						mapa.put(id, estat);
						questRepetibles--;
					}
					else if(mapa.get(id) == estat - 1) {
						mapa.put(id,  estat);
						if(estat == 3)
							questRepetibles++;
					}
					else
						error = true;
				}
				else {
					if(mapa.get(id) == estat - 1) {
						mapa.put(id,  estat);
						if(estat == 3)
							questNoRepetibles++;
					}
					else
						error = true;
				}
			}
			else if(estat == 0 || estat == 1)
				mapa.put(id, estat);
			else
				error = true;
		}
		System.out.print(questNoRepetibles + " ");
		System.out.print(questRepetibles + " ");
		if(error)
			System.out.print("E");
	}
	
	static void exercici2(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int mida = ohowo.nextInt();
		ohowo.nextLine();
		String[][] matriu = new String[mida][mida];
		
		for(int i = 0; i < mida; i++) {
			for(int j = 0; j < mida; j++) {
				matriu[i][j] = ohowo.next();
			}
		}
		int num = ohowo.nextInt();
		int iguals = 0;
		boolean igual = true;
		//Comprovar lletres
		
		for(int j = 0; j < mida; j++) {
			if(matriu[num][j].equals(matriu[j][num]))
				iguals++;
			else
				igual = false;
		}
		
		System.out.print(iguals + " " + igual);
	}

	static void exercici3(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int f = ohowo.nextInt();
		int c = ohowo.nextInt();
		int[][] matriu = new int[f][c];
		
		for(int i = 0; i < f; i++) {
			for(int j = 0; j < c; j++) {
				matriu[i][j] = ohowo.nextInt();
			}
		}
		int posX = ohowo.nextInt();
		int posY = ohowo.nextInt();
		int max = matriu[posX][posY];
		char moviment = 0;
		
		if(posX > 0 && matriu[posX-1][posY] > max) {
			max = matriu[posX-1][posY];
			moviment = 'W';
		}
		if(posY > 0 && matriu[posX][posY-1] > max) {
			max = matriu[posX][posY-1];
			moviment = 'A';
		}
		if(posY < c-1 && matriu[posX][posY+1] > max) {
			max = matriu[posX][posY+1];
			moviment = 'D';
		}
		if(posX < f-1 && matriu[posX+1][posY] > max) {
			max = matriu[posX+1][posY];
			moviment = 'S';
		}
		System.out.println(moviment);
	}
	
	public static void main(String[] args) {
		//exercici1(null);
		//exercici2(null);
		exercici3(null);
	}
}