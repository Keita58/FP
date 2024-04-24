package ohowo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class exercici4 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		ArrayList<String> llista = new ArrayList<>(Arrays.asList(ohowo.nextLine().split(";")));
		int count = 0;
		
		for(int i = 0; i < llista.size(); i++) {
			int aux = llista.get(i).charAt(0); 
			if(aux >= 97 && aux <= 122)
				count++;
		}
		System.out.println(count);
	}
}
