package UF1.Buscar;
import java.util.Scanner;

public class buscar_caracter {

	public static void main(String[] args) {
		Scanner ohwow = new Scanner(System.in);
		
		int casos  = ohwow.nextInt();
		ohwow.nextLine();
		
		for(int x = 0; x < casos; x++) {
			String frase = ohwow.nextLine();
			int a, e, i, o, u;
			a = e = i = o = u = 0;
			for(int j = 0; j < frase.length(); j++) {
				if((frase.charAt(j)=='a') || (frase.charAt(j)=='A')) 
					a++;
				else if((frase.charAt(j)=='e') || (frase.charAt(j)=='E'))
					e++;
				else if((frase.charAt(j)=='i') || (frase.charAt(j)=='I'))
					i++;
				else if((frase.charAt(j)=='o') || (frase.charAt(j)=='O'))
					o++;
				else if((frase.charAt(j)=='u') || (frase.charAt(j)=='U'))
					u++;
			}
			System.out.println("A: "+a+" E: "+e+" I: "+i+" O: "+o+" U: "+u);
		}
	}
}
