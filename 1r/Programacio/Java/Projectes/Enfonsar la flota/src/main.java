import java.util.Random;
import java.util.Scanner;

public class main {
	static Scanner ohowo = new Scanner(System.in); // Ho posem com a variable pública ja que la necessitarem en diferents funcions
	static int DIMENSIO = 10; //Aquí igual
	
	public static void main(String[] args) {
		char[][] mapaVisible = new char[DIMENSIO][DIMENSIO]; // 'X' Es l'estat inicial -> '1' tocat i '0' aigua
		char[][] mapaInvisible = new char[DIMENSIO][DIMENSIO]; // '1' vaixell i '0' aigua
		int torn = 1;
		int[] secret = {0};
		boolean acabat = false;
		incialitzarMapa(mapaVisible, mapaInvisible); //El posem fora ja que només es necessita inicialitzar una vegada
		
		while(!acabat) {
			mostra(mapaVisible);
			Coord moviment = coordenades();
			tocat(mapaVisible, mapaInvisible, moviment, secret);
			acabat = finalPartida(mapaVisible, torn);
			
			if(acabat)
				break;
			else
				torn++;
		}
	}

	private static boolean finalPartida(char[][] mapaVisible, int torn) {
		int sub = 0;
		
		for(int i = 0; i < DIMENSIO; i++) {
			for(int j = 0; j < DIMENSIO; j++) {
				if(mapaVisible[i][j] == '1')
					sub++;
			}
		}
		
		if(sub == 8) {
			System.out.println("Felicitats! Has acabat amb tots els vaixells en " + torn + " torns.");
			return true;
		}
		else
			return false;
	}

	private static void tocat(char[][] mapaVisible, char[][] mapaInvisible, Coord moviment, int[] secret) {
		if(mapaInvisible[moviment.X][moviment.Y] == '1') {
			mapaVisible[moviment.X][moviment.Y] = '1';
			System.out.println("L'has tocat!");
			secret[0] = 0;
		}
		else {
			mapaVisible[moviment.X][moviment.Y] = '0';
			System.out.println("Aigua... :(");
			secret[0]++;
		}
		
		if(secret[0] == 3)
			System.out.println("T'està costant una mica, eh? Deus estar una mica rovellat.");
		else if(secret[0] == 6) 
			System.out.println("Avui no es el teu dia.");
		else if(secret[0] == 9) 
			System.out.println("Millor posa't a jugar a l'Oca, tindràs més sort.");
		else if(secret[0] == 12) 
			System.out.println("Para, t'estàs fent mal a tu mateix amb tant intents.");
		else if(secret[0] == 15) 
			System.out.println("En serio, no juguis més. Ves a dormir que demà serà un nou dia.");
		else if(secret[0] == 18) 
			System.out.println("Decepcionant...");
		
		System.out.println();
	}

	private static void mostra(char[][] mapaVisible) {
		int lletra = 65;
		
		System.out.println("El taulell ara mateix està així: ");
		System.out.print("  ");
		for(int i = 0 ; i < DIMENSIO; i++) {
			System.out.print((i + 1) + " ");
		}
		System.out.println();
		
		for(int i = 0; i < DIMENSIO; i++) {
			for(int j = -1; j < DIMENSIO; j++) {
				if(j == -1) {
					System.out.print((char) lletra + " ");
					lletra++;
				}
				else
					System.out.print(mapaVisible[i][j] + " ");	
			}
			System.out.println();
		}
	}

	private static Coord coordenades() {
		Coord mov = new Coord();
		System.out.println("Escriu les coordenades del teu atac (fila,columna): ");
		
		while(true) {	
			try {
				String[] b = ohowo.nextLine().split(",");
				char aux = b[0].charAt(0);
				mov.X = aux - 96;
				mov.Y = Integer.parseInt(b[1]);
				if(mov.X <= 10 && mov.X >= 1 && mov.Y <= 10 && mov.Y >= 1) {
					mov.X--;
					mov.Y--;
					break;
				}
				else
					System.out.println("Coordenades incorrectes, si us plau introdueix noves coordenades: ");
			}
			catch(Exception e) {
				System.out.println("Coordenades incorrectes, si us plau introdueix noves coordenades: ");
			}
		}
		return mov;
	}

	private static void incialitzarMapa(char[][] mapaVisible, char[][] mapaInvisible) {
		int sub = 8;
		
		for(int i = 0; i < DIMENSIO; i++) {
			for(int j = 0; j < DIMENSIO; j++) {
				mapaInvisible[i][j] = '0';
			}
		}
		
		while(sub > 0) {
			Random aux = new Random();
			int posX = aux.nextInt(10);
			int posY = aux.nextInt(10);
			
			if(mapaInvisible[posX][posY] == '0') {
				boolean possible = true;
				if(posX > 0 && posY > 0 && possible && mapaInvisible[posX - 1][posY - 1] == '1') {
					possible = false;
				}
				if(posX > 0 && possible && mapaInvisible[posX - 1][posY] == '1') {
					possible = false;
				}
				if(posX > 0 && posY < DIMENSIO - 1 && possible && mapaInvisible[posX - 1][posY + 1] == '1') {
					possible = false;
				}
				if(posY > 0 && possible && mapaInvisible[posX][posY - 1] == '1') {
					possible = false;
				}
				if(posY < DIMENSIO - 1 && possible && mapaInvisible[posX][posY + 1] == '1') {
					possible = false;
				}
				if(posX < DIMENSIO - 1 && posY > 0 && possible && mapaInvisible[posX + 1][posY - 1] == '1') {
					possible = false;
				}
				if(posX < DIMENSIO - 1 && possible && mapaInvisible[posX + 1][posY] == '1') {
					possible = false;
				}
				if(posX < DIMENSIO - 1 && posY < DIMENSIO - 1 && possible && mapaInvisible[posX + 1][posY + 1] == '1') {
					possible = false;
				}
				
				if(possible) {
					mapaInvisible[posX][posY] = '1';
					sub--;
				}
			}
				
		}
		
		for(int i = 0; i < DIMENSIO; i++) {
			for(int j = 0; j < DIMENSIO; j++) {
				mapaVisible[i][j] = ' ';
			}
		}
	}
}
