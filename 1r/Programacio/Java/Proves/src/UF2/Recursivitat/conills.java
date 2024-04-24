package UF2.Recursivitat;

import java.util.Scanner;

public class conills {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int num = ohowo.nextInt();
			int num1 = 1;
			int num2 = 0;
			int count = 0;
			int pos = rec(num, num1, num2, count);
			System.out.println(pos);
		}
	}

	private static int rec(int nextInt, int num1, int num2, int count) {
		count++;
		if(count%2 == 1) {
			num2 = num1 + num2;
			if(count == nextInt)
				return num1;
			count = rec(nextInt, num1, num2, count);
		}
		else {
			num1 = num1 + num2;
			if(count == nextInt)
				return num2;
			count = rec(nextInt, num1, num2, count);
		}
		return count;
	}
}
