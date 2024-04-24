import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {
	public static void main(String[] args) {
		Scanner ohwow = new Scanner(System.in);
		
		String text = ohwow.nextLine(); //Per llegir text
		int num = ohwow.nextInt(); //Per llegir números
		double num2 = ohwow.nextDouble(); //Per llegir doubles
		boolean bool = ohwow.nextBoolean(); //Per llegir booleans
		System.out.println(text);
		System.out.println(num);
		System.out.println(num2);
		System.out.println(bool);
		
		/*
		 * String[] paraules = paraula.split("\\s+"); <- Separa un string pels espais en diferents strings
		 * 
		 */
	}
}
