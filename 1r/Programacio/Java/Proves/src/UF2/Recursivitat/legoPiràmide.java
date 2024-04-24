package UF2.Recursivitat;

import java.util.Scanner;

public class legoPiràmide {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int res = 0;
			res = pisos(ohowo.nextInt(), res);
			System.out.println(res);
		}
	}

	private static int pisos(int num, int res) {
		
		if(num > 2 && num%2 == 0) {
			res += num/2 + num/2 + (num/2)-2 + (num/2)-2;
			res = pisos(num-2, res);
		}
		else if (num == 2)
			res++;
			
		return res;
	}
}
