package UF1.Contar;
import java.util.Scanner;

public class contar_paraules {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		
		int casos = ohowo.nextInt();
		ohowo.nextLine();
		for(int i = 0; i < casos; i++) {
			String nom = ohowo.nextLine();
			if(nom.split("\\s+").length > 0) {
				nom = nom.strip();
				System.out.println(nom.split("\\s+").length);
			}
			else
				System.out.println(0);
		}
	}
}
