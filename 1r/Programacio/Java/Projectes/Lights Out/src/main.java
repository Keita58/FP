import java.util.Random;
import java.util.Scanner;

public class main {

	static Scanner ohowo = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Jugador jugador = new Jugador();
		int mida = menu(jugador);
		Casella[][] tauler = new Casella[mida][mida];
		incialitzarTauler(tauler, mida);
		
		while(true) {
			mostraTauler(tauler, mida);
			int[] coord = escollirCasella(mida);
			moviment(tauler, coord, mida, jugador);
			boolean victoria = haGuanyat(tauler, mida);
			
			if(victoria) {
				System.out.println("Felicitats! Has guanyat en " + jugador.moviments + " moviments.");
				break;
			}
		}
	}

	private static boolean haGuanyat(Casella[][] tauler, int mida) {
		boolean victoria = true;
		
		for(int i = 0; i < mida && victoria; i++) {
			for(int j = 0; j < mida && victoria; j++) {
				if(!tauler[i][j].ences)
					victoria = false;
			}
		}
		
		return victoria;
	}

	private static void moviment(Casella[][] tauler, int[] coord, int mida, Jugador jugador) {
		
		jugador.moviments++;
		
		//Canviem l'estat del centre
		tauler[coord[0]][coord[1]].ences = !tauler[coord[0]][coord[1]].ences;
		
		//Canviem l'estat de sobre
		if(coord[0] > 0) tauler[coord[0]-1][coord[1]].ences = !tauler[coord[0]-1][coord[1]].ences;
		
		//Canviem l'estat de l'esquerra
		if(coord[1] > 0) tauler[coord[0]][coord[1]-1].ences = !tauler[coord[0]][coord[1]-1].ences;
		
		//Canviem l'estat de la dreta
		if(coord[1] < mida-1)	tauler[coord[0]][coord[1]+1].ences = !tauler[coord[0]][coord[1]+1].ences;
		
		//Canviem l'estat de sota
		if(coord[0] < mida-1) tauler[coord[0]+1][coord[1]].ences = !tauler[coord[0]+1][coord[1]].ences;
	}

	private static int[] escollirCasella(int mida) {
		
		System.out.println("Escriu les coordenades que vols escollir: ");
		int[] pos = new int[2];
		
		while(true) {
			int x = ohowo.nextInt();
			int y = ohowo.nextInt();
			if(x >= mida || y >= mida)
				System.out.println("Coordenades incorrectes, escull unes altres: ");
			else {
				pos[0] = x;
				pos[1] = y;
				break;
			}
		}
		return pos;
	}

	private static void mostraTauler(Casella[][] tauler, int coord) {

		for(int i = 0; i < coord; i++) {
			for(int j = 0; j < coord; j++) {
				if(!tauler[i][j].ences)
					System.out.print("0 ");
				else if(tauler[i][j].ences)
					System.out.print("1 ");
			}
			System.out.println();
		}
	}

	private static void incialitzarTauler(Casella[][] tauler, int coord) {
		
		Random r = new Random();	
		
		for(int i = 0; i < coord; i++) {
			for(int j = 0; j < coord; j++) {
				int valor = r.nextInt(2);
				tauler[i][j] = new Casella();
				if(valor == 0) 
					tauler[i][j].ences = false;
				else if(valor == 1) 
					tauler[i][j].ences = true;
			}
		}
	}

	private static int menu(Jugador jugador) {
		
		int mida = 0;
		while(true) {
			System.out.println("Benvingut al Lights Out! Vols configurar el joc o jugar? (1/2)");
			int escrit = ohowo.nextInt();
			if(escrit == 1) {
				System.out.println("Escriu la mida de la matriu: ");
				mida = ohowo.nextInt();
				ohowo.nextLine();
				System.out.println("Escriu el teu nom: ");
				jugador.Nom = ohowo.nextLine();
			} 
			else if(escrit == 2){
				return mida;
			}
			else
				System.out.println("Escriu una opció vàlida.");
		}
	}
}
