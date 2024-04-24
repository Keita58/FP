package UF2.Recursivitat;

import java.util.Scanner;

public class bitlles {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int num = ohowo.nextInt();
			int suma = 0;
			int count = 0;
			int total = rec(num, suma, count);
			System.out.println(total);
		}
	}

	private static int rec(int num, int suma, int count) {
		if(count == num)
			return suma;
		else {
			suma += (count + 1);
			count++;
			return rec(num, suma, count);
		}
	}
}
