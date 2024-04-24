package ExercicisDificils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

public class barogangplank {
	
	/* * * * * * * * * * * * * * * * * *
	 * Aquí mirarem de trobar quin dels dos barris ens va millor agafar,
	 *  si l'esquerra (1) o la dreta (0) i ho tornem a cami
	 *  
	 *  IMPORTANT!!!!!!!!!!!
	 *  Es pot saber si guanyaràs o no mirant el número de Ps que queden
	 *  Si queden senars i es el teu torn guanyaràs,
	 *  si queden parells perdràs.
	 *  
	 *  A part, has de mirar si el nombre de Ps que hi ha en les dues parts de l'array
	 *  son el mateix, ja que han d'estar equilibrats.
	 * * * * * * * * * * * * * * * * * */
	static int quinAgafo(ArrayList<String> llista) { 
		int cami = 0;
		/*float esquerraC = 0;
		float dretaC = 0;
		float esquerraP = 0;
		float dretaP = 0;
		
		for(int i = 0; i < llista.size()/2; i++) {
			if(llista.get(i).equals("C") && llista.get(llista.size()-i-1).equals("C")) {
				esquerraC++;
				dretaC++;
			}
			else if(llista.get(i).equals("P") && llista.get(llista.size()-i-1).equals("C")) {
				dretaC++;
				esquerraP++;
			}
			else if(llista.get(i).equals("C") && llista.get(llista.size()-i-1).equals("P")) {
				esquerraC++;
				dretaP++;
			}
			else if(llista.get(i).equals("P") && llista.get(llista.size()-i-1).equals("P")) {
				esquerraP++;
				dretaP++;
			}
		}*/
		if(llista.get(1).equals("P") && llista.get(llista.size()-2).equals("C"))
			cami = 1;
		else if(llista.get(1).equals("P") && llista.get(llista.size()-2).equals("P"))
			cami = 0;
		
		return cami;
	}
	
	static void bucle(ArrayList<String> llista, ListIterator<String> iterador, int n, int k) {
		int torn = 1;
		int barrilsGang = 0;
		int barrilsJo = 0;
		boolean perdreGang = false;
		boolean perdreJo = false;
		
		while(!perdreGang && !perdreJo) {
			if(llista.get(0).equals("C") && llista.get(iterador.previousIndex()).equals("C") && torn%2 == 0) {
				barrilsJo++;
				if(barrilsJo == k)
					perdreJo = true;
				else { //Aquí mirarem quin barril agafar per jugar perfecte
					int cami = quinAgafo(llista); //Passem la llista per fer els càlculs
					if(cami == 0) {
						llista.remove(0);
						iterador = llista.listIterator(llista.size());
					}
					else {
						iterador.previous();
						iterador.remove();
					}
				}
			}
			else if(llista.get(0).equals("C") && llista.get(iterador.previousIndex()).equals("C") && torn%2 == 1) {
				barrilsGang++;
				if(barrilsGang == k)
					perdreGang = true;
				else {  //Aquí mirarem quin barril agafar per jugar perfecte
					int cami = quinAgafo(llista); //Passem la llista per fer els càlculs
					if(cami == 0) {
						llista.remove(0);
						iterador = llista.listIterator(llista.size());
					}
					else {
						iterador.previous();
						iterador.remove();
					}
				}
			}
			else if(llista.get(0).equals("C") && llista.get(iterador.previousIndex()).equals("P")) {
				iterador.previous();
				iterador.remove();
			}
			else if(llista.get(0).equals("P") && llista.get(iterador.previousIndex()).equals("C")) {
				llista.remove(0);
				iterador = llista.listIterator(llista.size());
			}
			else if(llista.get(0).equals("P") && llista.get(iterador.previousIndex()).equals("P")) {
				iterador.previous();
				iterador.remove();
			}
			torn++;
		}
		
		if(perdreGang)
			System.out.println("JO");
		else if(perdreJo)
			System.out.println("GANGPLANK");
	}
	
	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int n = ohowo.nextInt();
		int k = ohowo.nextInt();
		
		ArrayList<String> llista = new ArrayList<>(Arrays.asList(ohowo.next().split("")));
		ListIterator<String> iterador = llista.listIterator(llista.size());
		bucle(llista, iterador, n, k);
	}
}
