import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		
		Scanner ohowo = new Scanner(System.in);
		
		int torn = 0;
		System.out.println("Escriu la mida de la matriu de joc: ");
		int N = ohowo.nextInt();
		int M = ohowo.nextInt();
		ohowo.nextLine();
		int cartesTapades = N*M;
		boolean jugar = false;
		Fitxa[][] tauler = new Fitxa[N][M];
		Jugador[] jugadors = new Jugador[2];
		jugadors[0] = new Jugador();
		jugadors[1] = new Jugador();
		Random r = new Random();
		
		inicialitzarJugadors(jugadors, ohowo);
		torn = r.nextInt(2);
		inciarTauler(N, M, tauler, ohowo);
		jugar = menu(ohowo, jugadors);
		
		while(jugar) {
			mostrar(torn, jugadors, tauler, N, M, true);
			Coord carta1 = moviment(tauler, ohowo, N, M);
			mostrar(torn, jugadors, tauler, N, M, true);
			Coord carta2 = moviment(tauler, ohowo, N, M);
			mostrar(torn, jugadors, tauler, N, M, false);
			boolean iguals = comprova(carta1, carta2, tauler);
			
			if(iguals) {
				cartesTapades -= 2;
				if(torn%2 == 0)
					jugadors[0].nPunts++;
				else
					jugadors[1].nPunts++;
				System.out.println("L'has encertat!");
				System.out.println();
			}
			else {
				System.out.println("Has fallat!");
				System.out.println();
				torn++;
			}
			
			boolean victoria = haGuanyat(tauler, jugadors, cartesTapades); //Si ha guanyat imprimirem el nom del jugador
			
			if(victoria) {
				boolean sortir = menu(ohowo, jugadors);
				if(sortir == false)
					break;
				else {
					torn = r.nextInt(2);
					inciarTauler(N, M, tauler, ohowo);
				}
			}
		}
	}

	private static boolean comprova(Coord carta1, Coord carta2, Fitxa[][] tauler) {
		
		boolean iguals = false;
		if(tauler[carta1.x][carta1.y].numFitxa == (tauler[carta2.x][carta2.y].numFitxa)) {
			iguals = true;
			tauler[carta1.x][carta1.y].girada = true;
			tauler[carta2.x][carta2.y].girada = true;
		}
		else {
			tauler[carta1.x][carta1.y].girada = false;
			tauler[carta2.x][carta2.y].girada = false;
		}
		return iguals;
	}

	private static boolean haGuanyat(Fitxa[][] tauler, Jugador[] jugadors, int cartesTapades) {
		
		if(cartesTapades <= 1) {
			if(jugadors[0].nPunts > jugadors[1].nPunts) {
				System.out.println("Ha guanyat el jugador " + jugadors[0].nomJugador + "!");
				jugadors[0].nVictories++;
			}
			else if(jugadors[0].nPunts < jugadors[1].nPunts) {
				System.out.println("Ha guanyat el jugador " + jugadors[1].nomJugador + "!");
				jugadors[1].nVictories++;
			}
			else if(jugadors[0].nPunts == jugadors[1].nPunts) {
				System.out.println("Els dos jugadors han empatat!");
				jugadors[0].nVictories++;
				jugadors[1].nVictories++;
			}
			return true;
		}
		return false;
	}

	private static Coord moviment(Fitxa[][] tauler, Scanner ohowo, int n, int m) {
		
		Coord carta = new Coord();
		while(true) {
			System.out.println("Escriu les coordenades de la carta que vols escollir: ");
			int x = ohowo.nextInt();
			int y = ohowo.nextInt();
			
			if(x >= n || y >= m || tauler[x][y].girada) {
				System.out.println("Coordenades errònies, torna a escriure les teves coordenades.");
			}
			else {
				carta.x = x;
				carta.y = y;
				tauler[x][y].girada = true;
				break;
			}
		}
		return carta;
	}

	private static void mostrar(int torn, Jugador[] jugadors, Fitxa[][] tauler, int N, int M, boolean b) {
		
		if(b) {
			if(torn%2 == 0) {
				System.out.println("Torn del jugador " + jugadors[0].nomJugador);
			}
			else {
				System.out.println("Torn del jugador " + jugadors[1].nomJugador);
			}
		}
		
		System.out.println("Llegenda: X -> Cartes girades / O -> Cartes no girades");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(tauler[i][j].girada)
					System.out.print(tauler[i][j].numFitxa+" ");
				else
					System.out.print("O ");
			}
			System.out.println();
		}
	}

	private static boolean menu(Scanner ohowo, Jugador[] jugadors) {
		
		boolean sortir = false;
		System.out.println("Benvingut al joc del memory! Si vols jugar escriu 1, si vols veure les victòries escriu 2, si vols sortir escriu 3");
		
		while(!sortir) {
			int menu = ohowo.nextInt();
			switch(menu) {
			case 1:
				return true;
			case 2:
				System.out.println("El jugador " + jugadors[0].nomJugador + " té " + jugadors[0].nVictories + " victòries");
				System.out.println("El jugador " + jugadors[1].nomJugador + " té " + jugadors[1].nVictories + " victòries");
				break;
			case 3:
				sortir = true;
			}
		}
		ohowo.nextLine();
		return false;
	}

	private static void inciarTauler(int N, int M, Fitxa[][] tauler, Scanner ohowo) {
		
		ArrayList<Integer> aux = new ArrayList<>();
		for(int i = 0; i < (N*M)/2; i++) {
			aux.add(i+1);
			aux.add(i+1);
		}
		Collections.shuffle(aux);
		int valor = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				tauler[i][j] = new Fitxa();
				tauler[i][j].girada = false;
				tauler[i][j].numFitxa = aux.get(valor);
				valor++;
			}
		}
	}

	private static void inicialitzarJugadors(Jugador[] jugadors, Scanner ohowo) {

		System.out.println("Escriu el nom del jugador 1: ");
		jugadors[0].nomJugador = ohowo.nextLine();
		
		System.out.println("Escriu el nom del jugador 2: ");
		jugadors[1].nomJugador = ohowo.nextLine();
	}
}
