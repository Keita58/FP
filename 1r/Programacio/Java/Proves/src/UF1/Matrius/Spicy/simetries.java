package UF1.Matrius.Spicy;

import java.util.Scanner;

public class simetries {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int f = ohowo.nextInt();
			int c = ohowo.nextInt();
			boolean vertical, horitzontal;
			vertical = horitzontal = true;
			char[][] matriu = new char[f][c];
			ohowo.nextLine();
			
			for(int j = 0; j < f; j++) {
				for(int k = 0; k < c; k++) {
					matriu[j][k] = ohowo.next().charAt(0);
				}
			}
			//Primer mirem la vertical
			for(int j = 0; j < f && vertical; j++) {
				for(int k = 0; k < c/2 && vertical; k++) {
					if(matriu[j][k] != matriu[j][c-k-1])
						vertical = false;
				}
			}
			//Després la horitzontal
			for(int j = 0; j < f/2 && horitzontal; j++) {
				for(int k = 0; k < c && horitzontal; k++) {
					if(matriu[j][k] != matriu[f-1-j][k])
						horitzontal = false;
				}
			}
			
			if(vertical && horitzontal)
				System.out.println("DOBLE");
			else if(vertical)
				System.out.println("VERTICAL");
			else if(horitzontal)
				System.out.println("HORITZONTAL");
			else
				System.out.println("CAP");
		}
	}
}
