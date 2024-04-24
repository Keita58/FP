package UF2.Recursivitat;

import java.util.Scanner;

public class buscantE {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			double calcul = 0;
			System.out.println(rec(ohowo.nextInt(), calcul));
		}
	}

	private static double rec(int num, double calcul) {
		
		double f = factorial(num);
		if(num >= 1) {
			calcul += (1/f);
			return rec(num - 1, calcul);
		}
		else {
			return calcul += 1;
		}
			
	}

	private static int factorial(int num) {
	
	    if (num <= 2) {
	        return num;
	    }
	    return num * factorial(num - 1);
	}
}
