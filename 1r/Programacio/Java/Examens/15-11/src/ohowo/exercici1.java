package ohowo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class exercici1 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		ArrayList<String> llista1 = new ArrayList<>(Arrays.asList(ohowo.nextLine().split(" ")));
		ArrayList<String> llista2 = new ArrayList<>(Arrays.asList(ohowo.nextLine().split(" ")));
		String paraula = ohowo.nextLine();
		
		if(llista1.indexOf(paraula) != -1 && llista2.indexOf(paraula) != -1) {
			String aux = llista1.get(llista1.indexOf(paraula));
			int pos = llista1.indexOf(paraula);
			llista1.set(pos, llista2.get(llista1.indexOf(paraula)));
			llista2.set(pos, aux);
			
			aux = llista2.get(llista2.indexOf(paraula));
			pos = llista2.indexOf(paraula);
			llista2.set(pos, llista1.get(llista2.indexOf(paraula)));
			llista1.set(pos, aux);
			
			System.out.println(llista1);
			System.out.println(llista2);
		}
		else
			System.out.println("NOPE");
		
 	}
}
