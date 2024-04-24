package ExercicisDificils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class despaçament {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		String paraula = ohowo.nextLine();
		StringBuilder cadena = new StringBuilder(ohowo.nextLine());
		boolean trobat = false;
		
		for(int i = 0; i < cadena.length() && !trobat; i++) {
			if(paraula.contains(cadena))
				trobat = true;
			else {
				char aux = cadena.charAt(0);
				cadena.deleteCharAt(0);
				cadena.append(aux);
			}
		}
		if(trobat)
			System.out.println("yes");
		else
			System.out.println("no");
	}
}
