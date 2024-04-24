package UF1.Contar;
import java.util.Scanner;

public class contar_lletres {

	public static void main(String[] args) {
		Scanner ohwow = new Scanner(System.in);
		String frase = ohwow.nextLine();
		
		while(!frase.equals("FI")) {
			int count = 0;
			for(int i = 0; i < frase.length(); i++) {
				if(Character.isLetter(frase.charAt(i)))
					count++;
			}
			System.out.println(count);
			frase = ohwow.nextLine();
		}
	}
}
