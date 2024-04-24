package UF1.Exercicis_sense_resoldre;
import java.util.Arrays;
import java.util.Scanner;

public class un_lalafel {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();

		for(int i = 0; i < casos; i++) {
			ohowo.nextLine();
			String frase = ohowo.nextLine();
			int capacitat = ohowo.nextInt();
			int lalafels = 0;
			int teranyina = 0;
			boolean aranya = false;
			
			String[] tipus = frase.split(", ");
			for(int j = 0; j < tipus.length; j++) {
				if(tipus[j].toLowerCase().equals("lalafel"))
					lalafels++;
				else if(tipus[j].toLowerCase().equals("telaranya")) {
					teranyina++;
				}
				else
					aranya = true;
			}
			if(!aranya) {
				int count = 0;
				int teranyina_aux = teranyina;
				for(int j = 0; j < teranyina; j++)
					if(capacitat <= lalafels && teranyina_aux > 0 && capacitat > 0) {
						count++;
						teranyina_aux--;
						lalafels -= capacitat;
					}	
				System.out.println("Hay "+count+" telaranyas llenas.");
			}
			else
				System.out.println("Hay 0 telaranyas llenas.");
		}
	}
}
