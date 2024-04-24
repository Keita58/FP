package RepasExamens.UF2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class repas_examen_29_01 {
	
	public static ArrayList<Integer> baralla = new ArrayList<Integer>(Arrays.asList(1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8,8,9,9,9,9,10,10,10,10,11,11,11,11,12,12,12,12,13,13,13,13));
	static Random r = new Random();
	static Scanner ohowo = new Scanner(System.in);
	
	public static void uno() {
		
		Jugador[] jugadors = new Jugador[4];
		ArrayList<Integer> tauler = new ArrayList<>();
		ArrayList<Integer> taulerEspecial = new ArrayList<>();
		int torn = 0; // Per saber quin jugador està jugant en cada moment, utilitzarem aquesta variable torn que amb un %4 podrem saber a qui li toca jugar
		boolean inversio = false;
		init(jugadors);
		seleccionarCartaInici(tauler);
		
		while(true) {			
			mostra(inversio, jugadors, torn, tauler, taulerEspecial);
			tirarCarta(tauler, taulerEspecial, jugadors, torn);
			switch(canviTorn(taulerEspecial)) {
			case 1:
				if(inversio)
					torn+=3;
				else
					torn++;
				break;
			case 2:
				inversio = (inversio == false ? true : false);
				break;
			case 3:
				if(inversio)
					torn -= 3;
				else 
					torn--;
				break;
			}
			if(fiPartida(jugadors, torn)) {
				System.out.println("Felicitats " + jugadors[torn%4].nom + "!! Has guanyat!");
				break;
			}
			if(inversio)
				torn+=3;
			else
				torn++;
		}		
	}
	
	private static boolean fiPartida(Jugador[] jugadors, int torn) {
		
		if(jugadors[Math.abs(torn%4)].cartes.isEmpty())
			return true;
		return false;
	}

	private static int canviTorn(ArrayList<Integer> taulerEspecial) {
		
		if(!taulerEspecial.isEmpty()) {
			switch(taulerEspecial.get(taulerEspecial.size() - 1)) {
			case 11:
				return 1;
			case 12:
				return 2;
			case 13:
				return 3;
			}
		}
		return 0;
	}

	private static void mostra(boolean inversio, Jugador[] jugadors, int torn, ArrayList<Integer> tauler, ArrayList<Integer> taulerEspecial) {
		
		System.out.print("La carta actual en el tauler es: ");
		if(taulerEspecial.isEmpty())
			System.out.print(tauler.get(tauler.size() - 1));
		else {
			System.out.print(taulerEspecial.get(taulerEspecial.size() - 1));
			System.out.println();
			if(taulerEspecial.get(taulerEspecial.size() - 1) == 11) {
				if(inversio)
					System.out.println("Oh no! El/La " + jugadors[(torn-3)%4].nom + " no pot tirar perquè li han fet saltar el torn! :(");
				else
					System.out.println("Oh no! El/La " + jugadors[(torn-1)%4].nom + " no pot tirar perquè li han fet saltar el torn! :(");
			}
			else if(taulerEspecial.get(taulerEspecial.size() - 1) == 12)
				System.out.println("S'ha canviat el sentit del joc!");
			else
				System.out.println("El/La " + jugadors[torn%4].nom + " torna a tirar!");
		}
		System.out.println();
	}

	private static void tirarCarta(ArrayList<Integer> tauler, ArrayList<Integer> taulerEspecial, Jugador[] jugadors, int torn) {
		
		int carta = tauler.get(tauler.size() - 1);
		System.out.println(jugadors[torn%4].nom + ", quina carta vols tirar? (Començant des de la posició 0)");
		System.out.print("[");
		
		for(int i = 0; i < jugadors[torn%4].cartes.size() - 1; i++) {
			System.out.print(jugadors[torn%4].cartes.get(i)+",");
		}
		System.out.print(jugadors[torn%4].cartes.get(jugadors[torn%4].cartes.size() - 1)+"]");
		
		int pos = ohowo.nextInt();
		if(jugadors[torn%4].cartes.get(pos) < 11) {
			taulerEspecial.removeAll(taulerEspecial);
			if(esValida(jugadors[torn%4].cartes.get(pos), carta)) {
				tauler.add(jugadors[torn%4].cartes.get(pos));
				jugadors[torn%4].cartes.remove(pos);
			}
			else {
				robar(jugadors, torn);
				System.out.println("Has tirat una carta incorrecte! Robes una :(");
			}
		}
		else {
			taulerEspecial.add(jugadors[torn%4].cartes.get(pos));
			jugadors[torn%4].cartes.remove(pos);
		}
		System.out.println();
	}

	private static boolean esValida(Integer carta, int carta2) {
		
		if(carta == 1) {
			if(carta == carta2 || carta == carta2 - 1)
				return true;
			else
				return false;
		}
		if(carta == 10) {
			if(carta == carta2 || carta == carta2 + 1)
				return true;
			else
				return false;
		}
		else {
			if(carta == carta2 || carta == carta2 - 1 || carta == carta2 + 1)
				return true;
			else
				return false;
		}
	}

	private static void robar(Jugador[] jugadors, int torn) {
		
		int pos = r.nextInt(baralla.size());
		jugadors[torn%4].cartes.add(baralla.get(pos));
		baralla.remove(pos);
	}

	private static void seleccionarCartaInici(ArrayList<Integer> tauler) {
		
		while(true) {
			int pos = r.nextInt(baralla.size());
			if(baralla.get(pos) < 11) {
				tauler.add(baralla.get(pos));
				baralla.remove(pos);
				break;
			}
		}
	}

	private static void init(Jugador[] jugadors) {
		
		Collections.shuffle(baralla);
		
		for(int i = 0; i < jugadors.length; i++) {
			jugadors[i] = new Jugador();
			System.out.println("Benvingut a l'Uno, escriu el teu nom: ");
			jugadors[i].nom = ohowo.nextLine();
			for(int j = 0; j < 5; j++) 
				robar(jugadors, i);
		}
	}

	public static void main(String[] args) {
		uno();
	}
}
