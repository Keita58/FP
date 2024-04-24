package UF1.Matrius.Desplaçament;

import java.util.Scanner;

public class jocOs {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int fc = ohowo.nextInt();
			int count = 0;
			ohowo.nextLine();
			String[][] matriu = new String[fc][fc];
			
			for(int j = 0; j < fc; j++) {
				String[] aux = ohowo.next().split("");
				for(int k = 0; k < fc; k++) {
					matriu[j][k] = aux[k];
				}
				ohowo.nextLine();
			}
			
			for(int j = 0; j < fc; j++) {
				for(int k = 0; k < fc; k++) {
					if(k < (fc-1) && k > 0 && matriu[j][k-1].equals("O") && matriu[j][k].equals("S") && matriu[j][k+1].equals("O")) {
						count++;
					}
					if(j < (fc-1) && j > 0 && matriu[j-1][k].equals("O") && matriu[j][k].equals("S") && matriu[j+1][k].equals("O")) {
						count++;
					}
					if(j < (fc-1) && k < (fc-1) && j > 0 && k > 0 && matriu[j-1][k-1].equals("O") && matriu[j][k].equals("S") && matriu[j+1][k+1].equals("O")) {
						count++;
					}
					if(j < (fc-1) && k < (fc-1) && j > 0 && k > 0 && matriu[j-1][k+1].equals("O") && matriu[j][k].equals("S") && matriu[j+1][k-1].equals("O")) {
						count++;
					}
				}
			}
			System.out.println(count);
		}
	}

}
