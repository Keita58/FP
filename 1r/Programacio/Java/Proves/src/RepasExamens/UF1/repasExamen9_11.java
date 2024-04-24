package RepasExamens.UF1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class repasExamen9_11 {

	public static void exercici1(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int numPilots = ohowo.nextInt();
		ohowo.nextLine();
		String nomPilots = ohowo.nextLine();
		ArrayList<String> entrada = new ArrayList<>(Arrays.asList(nomPilots.split(" "))); 
		ArrayList<String> sortida = new ArrayList<>(Arrays.asList(nomPilots.split(" ")));
		
		for(int i = 0; i < numPilots; i++) {
			int mov = ohowo.nextInt();
			int pos = sortida.indexOf(entrada.get(i));
			sortida.remove(entrada.get(i));
			sortida.add(mov+pos, entrada.get(i));
		}
		System.out.println(sortida);
	}
	
	public static void exercici2(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		StringBuilder encriptar = new StringBuilder(ohowo.nextLine());
		int num1 = ohowo.nextInt();
		int num2 = ohowo.nextInt();
		int num3 = ohowo.nextInt();
		
		for(int i = num1; i < num2; i++) {
			char lletra = encriptar.charAt(i);
			lletra += num3;
			encriptar.setCharAt(i, lletra);
		}
		System.out.println(encriptar);
	}
	

	public static void exercici3(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		ArrayList<String> llista1 = new ArrayList<>(Arrays.asList(ohowo.nextLine().split(" ")));
		ArrayList<String> llista2 = new ArrayList<>(Arrays.asList(ohowo.nextLine().split(" ")));
		ArrayList<String> llista3 = new ArrayList<>(Arrays.asList(ohowo.nextLine().split(" ")));
		ArrayList<String> llista4 = new ArrayList<>(Arrays.asList(ohowo.nextLine().split(" ")));
		ArrayList<String> llista = new ArrayList<>();
		
		for(int i = 0; i < llista1.size(); i++) 
			if(llista2.contains(llista1.get(i)) && llista3.contains(llista1.get(i)) && llista4.contains(llista1.get(i)) && !llista.contains(llista1.get(i)))
				llista.add(llista1.get(i));

		Collections.sort(llista);
		System.out.println(llista);
	}

	public static void exercici4(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		
	}
	
	public static void mastermind(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int correctePos, correcteNum;
		correctePos = correcteNum = 0;
		ArrayList<Integer> correcte = new ArrayList<>();
		ArrayList<Integer> intent = new ArrayList<>();
		
		for(int i = 0; i < 4; i++) {
			correcte.add(ohowo.nextInt());
		}
		
		for(int i = 0; i < 4; i++) {
			intent.add(ohowo.nextInt());
		}
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(correcte.get(i) == intent.get(j) && i == j)
					correctePos++;
				else if(correcte.get(i) == intent.get(j))
					correcteNum++;
			}
		}
		ArrayList<String> aux = new ArrayList<>();
		for(int i = 0; i< 4; i++) {
			if(correctePos > 0) {
				aux.add("X");
				correctePos--;
			}
			if(correcteNum > 0) {
				aux.add("O");
				correcteNum--;
			}
		}
		Collections.sort(aux, Collections.reverseOrder());
		System.out.println(aux);
	}

	public static void main(String[] args) {
		//exercici1(null);
		//exercici2(null);
		//exercici3(null);
		//exercici4(null);
		mastermind(null);
	}

}
