import java.util.Scanner;

public class Exercici_4 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		String frase = ohowo.nextLine();
		int posUp, posDown;
		posUp = posDown = 0;
		boolean error = false;
		
		for(int x = 0; x < frase.length(); x++) {
			if(frase.charAt(x) == '¿' && posUp == 0) {
				posUp = x+1;
			}
			else if(frase.charAt(x) == '?' && posDown == 0) {
				posDown = x+1;
			}
			else if(frase.charAt(x) == '?' || frase.charAt(x) == '¿')
				error = true;
		}
		if(!error) {
			if(posUp == 0 && posDown > 0)
				System.out.println("CATALA");
			else if(posUp < posDown)
				System.out.println("CASTELLA");
			else if(posUp == 0 && posDown == 0)
				System.out.println("NO HI HA PREGUNTA");
			else
				System.out.println("ERROR");
		}
		else
			System.out.println("ERROR");
	}
}
