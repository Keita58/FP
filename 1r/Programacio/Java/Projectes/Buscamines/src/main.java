import java.util.Random;
import java.util.Scanner;

import Core.Board;
import Core.BoardSprite;
import Core.Sprite;
import Core.Window;


public class main {
	static Scanner ohowo = new Scanner(System.in);
	static Board b = new Board();
	static Window f = new Window(b);
	
	public static void main(String[] args) {
		
		int[] tauler = new int[3]; //1->x; 2->y; 3->mines;
		Jugador j1 = new Jugador();
		int op = menu(tauler, j1);
		boolean mina = false;
		
		while(op == 1 && !mina) {
			int punts = 0;
			int[][] taulerVisible = new int[tauler[0]][tauler[1]];
			int[][] taulerInvisible = new int[tauler[0]][tauler[1]];
			inicialitzarGUI(tauler);
			
			inicialitzaTaulers(taulerVisible, taulerInvisible, tauler);
			
			
			while(true) {
				mostra(taulerVisible);
				Coord a = coordenades(taulerVisible);
				if(comprova(a, taulerVisible, taulerInvisible)) {
					mina = fiPartidaMina();
					mostra(taulerInvisible);
					break;
				}
				else {
					punts++;
					descobrir(a, taulerVisible, taulerInvisible, tauler);
				}
				if(fiPartidaNormal(taulerVisible, taulerInvisible, tauler))
					break;
			}
			if(!mina) {
				mostra(taulerVisible);
				System.out.println("Felicitats!! Has guanyat!");
			}
			if(j1.puntuacioMax < punts)
				j1.puntuacioMax = punts;
			System.out.println("Vols tornar a jugar? (S/N)");
			if(ohowo.nextLine().toLowerCase().equals("n"))
				break;
			else {
				mina = false;
				op = menu(tauler, j1);
			}
		}
		System.out.println("Si us plau, tanca la finestra per acabar el joc.");
	}

	private static void inicialitzarGUI(int[] tauler) {
		
		b.setColorbackground(0xb1adad);
		b.setActborder(true);
		String[] lletres = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		b.setText(lletres);
		int[] colorlletres = { 0x0000FF, 0x00FF00, 0xFFFF00, 0xFF0000, 0xFF00FF, 0x00FFFF, 0x521b98, 0xFFFFFF, 0xFF8000,
				0x7F00FF };
		b.setColortext(colorlletres);
		f.setActLabels(true);
		String[] etiquetes2 = {"Mines: " + tauler[2]};
		f.setLabels(etiquetes2);
		f.setTitle("Buscamines");
	}

	private static boolean fiPartidaNormal(int[][] taulerVisible, int[][] taulerInvisible, int[] tauler) {
		
		for(int i = 0; i < taulerVisible.length; i++) {
			for(int j = 0; j < taulerVisible[i].length; j++) {
				if(taulerVisible[i][j] == 9 && taulerInvisible[i][j] == 0)
					return false;
			}
		}
		
		return true;
	}

	private static void descobrir(Coord a, int[][] taulerVisible, int[][] taulerInvisible, int[] tauler) {
		
		int num = destapar(a, taulerInvisible, tauler);
		if(num == 0) {
			taulerVisible[a.X][a.Y] = num;
			Coord b = new Coord();
			if(a.X > 0 && a.Y > 0 && taulerVisible[a.X - 1][a.Y - 1] == 9) {
				b.X = a.X - 1;
				b.Y = a.Y - 1;
				descobrir(b, taulerVisible, taulerInvisible, tauler);
			}
			if(a.X > 0 && taulerVisible[a.X - 1][a.Y] == 9) {
				b.X = a.X - 1;
				b.Y = a.Y;
				descobrir(b, taulerVisible, taulerInvisible, tauler);
			}
			if(a.X > 0 && a.Y < tauler[1] - 1 && taulerVisible[a.X - 1][a.Y + 1] == 9) {
				b.X = a.X - 1;
				b.Y = a.Y + 1;
				descobrir(b, taulerVisible, taulerInvisible, tauler);
			}
			if(a.Y > 0 && taulerVisible[a.X][a.Y - 1] == 9){
				b.X = a.X;
				b.Y = a.Y - 1;
				descobrir(b, taulerVisible, taulerInvisible, tauler);
			}
			if(a.Y < tauler[1] - 1 && taulerVisible[a.X][a.Y + 1] == 9){
				b.X = a.X;
				b.Y = a.Y + 1;
				descobrir(b, taulerVisible, taulerInvisible, tauler);
			}
			if(a.X < tauler[0] - 1 && a.Y > 0 && taulerVisible[a.X + 1][a.Y - 1] == 9){
				b.X = a.X + 1;
				b.Y = a.Y - 1;
				descobrir(b, taulerVisible, taulerInvisible, tauler);
			}
			if(a.X < tauler[0] - 1 && taulerVisible[a.X + 1][a.Y] == 9){
				b.X = a.X + 1;
				b.Y = a.Y;
				descobrir(b, taulerVisible, taulerInvisible, tauler);
			}
			if(a.X < tauler[0] - 1 && a.Y < tauler[1] - 1 && taulerVisible[a.X + 1][a.Y + 1] == 9){
				b.X = a.X + 1;
				b.Y = a.Y + 1;
				descobrir(b, taulerVisible, taulerInvisible, tauler);
			}
		}
		else
			taulerVisible[a.X][a.Y] = num;
	}

	private static int destapar(Coord a, int[][] taulerInvisible, int[] tauler) {
		
		int bombes = 0;
		
		if(a.X > 0 && a.Y > 0 && taulerInvisible[a.X - 1][a.Y - 1] == 1)
			bombes++;
		if(a.X > 0 && taulerInvisible[a.X - 1][a.Y] == 1)
			bombes++;
		if(a.X > 0 && a.Y < tauler[1] - 1 && taulerInvisible[a.X - 1][a.Y + 1] == 1)
			bombes++;
		if(a.Y > 0 && taulerInvisible[a.X][a.Y - 1] == 1)
			bombes++;
		if(a.Y < tauler[1] - 1 && taulerInvisible[a.X][a.Y + 1] == 1)
			bombes++;
		if(a.X < tauler[0] - 1 && a.Y > 0 && taulerInvisible[a.X + 1][a.Y - 1] == 1)
			bombes++;
		if(a.X < tauler[0] - 1 && taulerInvisible[a.X + 1][a.Y] == 1)
			bombes++;
		if(a.X < tauler[0] - 1 && a.Y < tauler[1] - 1 && taulerInvisible[a.X + 1][a.Y + 1] == 1)
			bombes++;
		return bombes;
	}

	private static boolean fiPartidaMina() {
		
		System.out.println("Ohno! Has pitjat una bomba! :(");
		System.out.println("Aquesta era la distribució de mines a la partida.");
		System.out.println("Fi de la partida.");
		return true;
	}

	private static boolean comprova(Coord a, int[][] taulerVisible, int[][] taulerInvisible) {
		
		if(taulerInvisible[a.X][a.Y] == 1)
			return true;
		else 
			return false;
	}

	private static Coord coordenades(int[][] taulerVisible) {
		
		Coord jugador = new Coord();
		jugador.X = -1;
		jugador.Y = -1;
//		while(true) {
//			System.out.println("Indica les coordenades que vols picar (x,y): ");
//			String aux = ohowo.nextLine();
//			String[] a = aux.split(",");
//			jugador.X = Integer.parseInt(a[0]);
//			jugador.Y = Integer.parseInt(a[1]);
//			if(jugador.X < taulerVisible.length && jugador.Y < taulerVisible[0].length)
//				break;
//			else
//				System.out.println("Coordenades incorrectes. Si us plau, introdueix les coordenades dins dels límits del joc.");
//		}
////		
		f.setActLabels(false);
		do {

			jugador.X = b.getCurrentMouseRow();
			jugador.Y = b.getCurrentMouseCol();
//			if (jugador.Y != -1 && jugador.X != -1) {
//				System.out.println("S'ha apretat el bot� esquerra en la fila " + jugador.X + " columna " + jugador.Y);
//			}
			try {
				Thread.sleep(100);  ///donem una mica de retard per no colapsar el buffer del mouse.
			} catch (InterruptedException e) {} 
		} while (jugador.X == -1 && jugador.Y == -1);

		return jugador;
	}

	private static void mostra(int[][] taulerVisible) {
		
		b.draw(taulerVisible, 't');
		
//		for(int i = 0; i < taulerVisible.length; i++) {
//			for(int j = 0; j < taulerVisible[i].length; j++) {
//				System.out.print(taulerVisible[i][j] + " ");
//			}
//			System.out.println();
//		}
	}

	private static void inicialitzaTaulers(int[][] taulerVisible, int[][] taulerInvisible, int[] coordMines) {
		
		for(int i = 0; i < taulerInvisible.length; i++) {
			for(int j = 0; j < taulerInvisible[i].length; j++) {
				taulerInvisible[i][j] = 0;
			}
		}
		int mines = coordMines[2];
		while(mines > 0) {
			Random rand = new Random();
			int x = rand.nextInt(coordMines[0]);
			int y = rand.nextInt(coordMines[1]);
			
			if(taulerInvisible[x][y] == 0) {
				taulerInvisible[x][y] = 1;
				mines--;
			}
		}
		
		for(int i = 0; i < taulerVisible.length; i++) {
			for(int j = 0; j < taulerVisible[i].length; j++) {
				taulerVisible[i][j] = 9;
			}
		}
	}

	private static int menu(int[] tauler, Jugador j1) {
		System.out.println("Benvingut/da al Buscamines! Quina opció vols triar?");
		System.out.println("1. Ajuda");
		System.out.println("2. Opcions");
		System.out.println("3. Jugar partida");
		System.out.println("4. Veure guanyadors");
		System.out.println("0. Sortir");
		int opcio = ohowo.nextInt();
					
		while(opcio >= 1) {
			switch(opcio) {
			case 0:
				return 0;
			case 1:
				System.out.println("El joc consisteix a netejar totes les caselles d'una pantalla que no amaguin una mina. Algunes caselles tenen un número. Aquest número indica les mines que sumen totes les caselles circumdants. \nAixí, si una casella té el número 3 vol dir que de les vuit caselles que l'envolten (excepte si es troba a una vora o una cantonada) n'hi ha 3 amb mines i 5 sense. \nSi es descobreix una casella sense número ens indica que cap de les caselles veïnes té mina i aquestes es descobreixen automàticament."
						+ "Si es descobreix una casella amb mina es perd la partida. ");
				break;
			case 2:
				opcions(tauler, j1);
				break;
			case 3:
				if(j1.Nom != null){
					ohowo.nextLine();
					return 1;
				}
				else {
					System.out.println("No has iniciat el joc. Ves a opcions abans de començar la partida.");
				}
				break;
			case 4:
				guanyadors(j1);
				break;
			default:
				System.out.println("Opció no existent, si su plau escull una de les opcions.");
				break;
			}
			System.out.println("Benvingut/da al Buscamines! Quina opció vols triar?");
			System.out.println("1. Ajuda");
			System.out.println("2. Opcions");
			System.out.println("3. Jugar partida");
			System.out.println("4. Veure guanyadors");
			System.out.println("0. Sortir");
			opcio = ohowo.nextInt();
		}
		return 0;
	}

	private static void guanyadors(Jugador j1) {

		if(j1.puntuacioMax == 0)
			System.out.println("No has jugat encara cap partida. Dóna-li un intent!");
		else
			System.out.println("La puntuació màxima actual és: " + j1.puntuacioMax + ".");
		
	}

	private static void opcions(int[] tauler, Jugador j1) {
		ohowo.nextLine(); //El Bug
		
		System.out.println("Benvingut/da a les opcions del Buscamines.");
		if(j1.Nom == null) {
			System.out.println("Defineix el teu nom: ");
			j1.Nom = ohowo.nextLine();
		}
		System.out.println("Defineix la mida del terreny (x,y)");
		String aux = ohowo.nextLine();
		String[] a = aux.split(",");
		tauler[0] = Integer.parseInt(a[0]);
		tauler[1] = Integer.parseInt(a[1]);
		System.out.println("Defineix les mines que vols que tingui el terreny: ");
		tauler[2] = ohowo.nextInt();
	}
}
