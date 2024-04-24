package RepasExamens.UF2;

import java.util.Scanner;

public class repas_examen_29_01_2 {

	public static void main(String[] args) {
		
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		ohowo.nextLine();
		
		
		for(int i = 0; i < casos; i++) {
			int num = 0;
			String gols = ohowo.nextLine();
			String[] golsYes = gols.split("");
			num = rec(golsYes, num);
			System.out.println(num);
		}
		
	}

	private static int rec(String[] golsYes, int num) {
		
		if(golsYes.length == 1)
			return num+Integer.parseInt(golsYes[0]);
		else {
			num += Integer.parseInt(golsYes[golsYes.length - 1]);
			String[] a = new String[golsYes.length - 1];
			for(int i = 0; i < a.length; i++)
				a[i] = golsYes[i];
			return rec(a, num);
		}
	}
}
