package UF1.Buscar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class buscar_strings {

	public static void main(String[] args) {
		Scanner ohwow = new Scanner(System.in);
		
		int casos = ohwow.nextInt();
		ohwow.nextLine();
		
		for(int i = 0; i < casos; i++) {
			String frase = ohwow.nextLine();
			int count = 0;
			Pattern p = Pattern.compile("la", Pattern.CASE_INSENSITIVE);
		    Matcher m = p.matcher(frase);
			
		    while(m.find())
					count++;
			
			System.out.println(count);
		}
	}
}
