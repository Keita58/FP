package UF1.Matrius.Spicy;

import java.util.ArrayList;
import java.util.Scanner;

public class sopaLletres {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int f = ohowo.nextInt();
		int c = ohowo.nextInt();
		ohowo.nextLine();
		ArrayList<String> llista = new ArrayList<>();
		char[][] matriu = new char[f][c];
		
		for(int i = 0; i < f; i++) {
			for(int j = 0; j < c; j++) {
				matriu[i][j] = ohowo.next().charAt(0);
			}
		}
		int t = ohowo.nextInt();
		ohowo.nextLine();
		
		for(int i = 0; i < t; i++) {
			llista.add(ohowo.nextLine());
		}
		
		for(int l = 0; l < llista.size(); l++) {
			boolean acabat = false;
			for(int i = 0; i < f && !acabat; i++) {
				for(int j = 0; j < c && !acabat; j++) {
					if(matriu[i][j] == llista.get(l).charAt(0)) {
						int countUp = 1;
						int countDown = 1;
						int countLeft = 1;
						int countRight = 1;
						if(llista.get(l).length() == 1)
							acabat = true;
						else {
							for(int k = 1; k < llista.get(l).length() && !acabat; k++) {
								if(i-k >= 0 && matriu[i-k][j] == llista.get(l).charAt(k)) {
									countUp++;
									if(countUp == llista.get(l).length())
										acabat = true;
								}
								if(j-k >= 0 && matriu[i][j-k] == llista.get(l).charAt(k)) {
									countLeft++;
									if(countLeft == llista.get(l).length())
										acabat = true;
								}
								if(j < c-k && matriu[i][j+k] == llista.get(l).charAt(k)) {
									countRight++;
									if(countRight == llista.get(l).length())
										acabat = true;
								}
								if(i < f-k && matriu[i+k][j] == llista.get(l).charAt(k)) {
									countDown++;
									if(countDown == llista.get(l).length())
										acabat = true;
								}
							}
						}
					}
				}
			}
			if(acabat)
				System.out.println("true");
			else
				System.out.println("false");
		}
	}
}
