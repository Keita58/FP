package UF1.Matrius.Desplaçament;

import java.util.Scanner;

public class clientes {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		char[][] matriu = new char[5][5];
		boolean malament = false;
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				matriu[i][j] = ohowo.next().charAt(0);
			}
		}
		
		for(int i = 0; i < 5 && !malament; i++) {
			for(int j = 0; j < 5 && !malament; j++) {
				char valor;
				if(matriu[i][j] == 'A')
					valor = 'B';
				else if(matriu[i][j] == 'B')
					valor = 'A';
				else if(matriu[i][j] == 'C')
					valor = 'D';
				else
					valor = 'C';
				if(i > 0 && j > 0 && matriu[i-1][j-1] == valor)
					malament = true;
				if(i > 0 && matriu[i-1][j] == valor)
					malament = true;
				if(i > 0 && j < 4 && matriu[i-1][j+1] == valor)
					malament = true;
				if(j > 0 && matriu[i][j-1] == valor)
					malament = true;
				if(j < 4 && matriu[i][j+1] == valor)
					malament = true;
				if(j > 0 && i < 4 && matriu[i+1][j-1] == valor)
					malament = true;
				if(i < 4 && matriu[i+1][j] == valor)
					malament = true;
				if(j < 4 && i < 4 && matriu[i+1][j+1] == valor)
					malament = true;
			}
		}
		if(malament)
			System.out.println("NO");
		else
			System.out.println("SI");
		
	}

}
