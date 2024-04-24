package ExercicisDificils;
import java.util.Scanner;

public class poker_daus {

	public static void main(String[] args) {
		Scanner ohwow = new Scanner(System.in);
		
		int dau1 = ohwow.nextInt();
		int dau2 = ohwow.nextInt();
		int dau3 = ohwow.nextInt();
		int dau4 = ohwow.nextInt();
		int count = 0;
		/*if(any%100 >= 90 && any%100 <= 99)
			System.out.println("SI");
		else
			System.out.println("NO"); -> Per saber si un any acaba entre 90 i 99*/
		
		boolean igual1 = (dau1 == dau2);
		boolean igual2 = (dau1 == dau3);
		boolean igual3 = (dau1 == dau4);
		boolean igual4 = (dau2 == dau3);
		boolean igual5 = (dau2 == dau4);
		boolean igual6 = (dau3 == dau4);
		boolean[] daus = {igual1, igual2, igual3, igual4, igual5, igual6};
		
		for(int i = 0; i < daus.length; i++) {
			if(daus[i])
				count++;
		}
		
		if(count == 6) {
			System.out.println("POKER");
		}
		else if(((dau1 == dau2) && dau1 == dau3) || ((dau1 == dau2) && dau1 == dau4) || ((dau1 == dau3) && dau1 == dau4) || ((dau2 == dau3) && dau2 == dau4)) {
			System.out.println("TRIO");
		}
		else if(igual1 || igual2 || igual3 || igual4 || igual5 || igual6) {
			System.out.println("PARELLA");
		}
		else
			System.out.println("RES");

	}

}
