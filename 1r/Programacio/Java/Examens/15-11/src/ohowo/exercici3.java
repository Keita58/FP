package ohowo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class exercici3 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		ArrayList<String> llista = new ArrayList<>(Arrays.asList(ohowo.nextLine().split("")));
		
		for(int i = 1; i < llista.size(); i++) {
			if(llista.get(i-1).equals(llista.get(i)) && !llista.get(i).equals("*"))
				llista.set(i,"*");
			else if(llista.get(i-1).equals("*") && llista.get(i).equals("*"))
				llista.set(i, "+");
		}
		
		for(int i = 0; i < llista.size(); i++) {
			System.out.print(llista.get(i));
		}
	}

}
