import java.util.Scanner;

public class exercici1 {
	
	static Scanner ohowo = new Scanner(System.in);

	public static void main(String[] args) {
		
		int[][] tauler = new int[4][4]; // 0 -> buit, 1 -> j1, 2 -> j2
		Jugador[] jugadors = new Jugador[2];
		init(tauler, jugadors);
		int torn = 0;
		boolean fi = false;
		while(true){
			if(jugadors[torn%2].fitxes > 0){
				mostra(tauler);
				Coord posicio = seleccionarPosicio(tauler, jugadors, torn);
				posarFitxa(posicio, tauler, torn);
				fi = comprovarLinea(tauler, posicio);
			} 
			else{
				mostra(tauler);
				Coord[] pos = moureFitxa(tauler, torn);
				canviarFitxa(pos, tauler, torn);
				fi = comprovarLinea(tauler, pos[1]);
			}
			if(fi) {
				mostra(tauler);
				System.out.println("Felicitats " + jugadors[torn%2].Nom + "! Has guanyat.");
				break;
			}
			torn++;
		}
	}

	private static void mostra(int[][] tauler) {
		
		for(int i = 0; i < tauler.length; i++) {
			for(int j = 0; j < tauler[i].length; j++) {
				System.out.print(tauler[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static boolean comprovarLinea(int[][] tauler, Coord posicio) {
		
		int count = 0;
		//Mirem si la fila hi ha 4 iguals
		for(int i = 0; i < tauler.length; i++) {
			if(tauler[posicio.X][posicio.Y] == 1)
				if(tauler[i][posicio.Y] == 1)
					count++;
			else if(tauler[posicio.X][posicio.Y] == 2)
				if(tauler[i][posicio.Y] == 2)
					count++;
		}
		if(count == 4)
			return true;
		
		//Mirem si la columna hi ha 4 iguals
		count = 0;
		for(int i = 0; i < tauler.length; i++) {
			if(tauler[posicio.X][posicio.Y] == 1)
				if(tauler[posicio.X][i] == 1)
					count++;
			else if(tauler[posicio.X][posicio.Y] == 2)
				if(tauler[posicio.X][i] == 2)
					count++;
		}
		if(count == 4)
			return true;
		
		//Mirem si la diagonal esquerra hi ha 4 iguals
		count = 0;
		for(int i = 0; i < tauler.length; i++) {
			if(tauler[posicio.X][posicio.Y] == 1)
				if(tauler[i][i] == 1)
					count++;
			else if(tauler[posicio.X][posicio.Y] == 2)
				if(tauler[i][i] == 2)
					count++;
		}
		if(count == 4)
			return true;
		
		//Mirem si la diagonal dreta hi ha 4 iguals
		count = 0;
		for(int i = 0; i < tauler.length; i++) {
			if(tauler[posicio.X][posicio.Y] == 1)
				if(tauler[i][3-i] == 1)
					count++;
			else if(tauler[posicio.X][posicio.Y] == 2)
				if(tauler[i][3-i] == 2)
					count++;
		}
		if(count == 4)
			return true;
		
		return false;
	}

	private static void canviarFitxa(Coord[] pos, int[][] tauler, int torn) {
		
		tauler[pos[0].X][pos[0].Y] = 0;
		posarFitxa(pos[1], tauler, torn);
	}

	private static Coord[] moureFitxa(int[][] tauler, int torn) {
		
		Coord[] aux = new Coord[2];
		while(true) {
			System.out.println("Tria la coordenada de la fitxa que vols canviar: ");
			aux[0] = new Coord();
			aux[0].X = ohowo.nextInt();
			aux[0].Y = ohowo.nextInt();
			if((aux[0].X >= 0 && aux[0].X < tauler.length && aux[0].Y >= 0 && aux[0].Y < tauler.length)) {
				if(torn%2 == 0 && tauler[aux[0].X][aux[0].Y] == 1) {
					System.out.println("Tria la coordenada on vols que vagi la fitxa: ");
					aux[1] = new Coord();
					aux[1].X = ohowo.nextInt();
					aux[1].Y = ohowo.nextInt();
					if((aux[1].X >= 0 && aux[1].X < tauler.length && aux[1].Y >= 0 && aux[1].Y < tauler.length) && (tauler[aux[1].X][aux[1].Y] == 0)) {
						break;
					}
					else
						System.out.println("Si us plau, escriu les coordenades correctes.");
				}
				else if(torn%2 == 1 && tauler[aux[0].X][aux[0].Y] == 2) {
					System.out.println("Tria la coordenada on vols que vagi la fitxa: ");
					aux[1] = new Coord();
					aux[1].X = ohowo.nextInt();
					aux[1].Y = ohowo.nextInt();
					if((aux[1].X >= 0 && aux[1].X < tauler.length && aux[1].Y >= 0 && aux[1].Y < tauler.length) && (tauler[aux[1].X][aux[1].Y] == 0)) {
						break;
					}
					else
						System.out.println("Si us plau, escriu les coordenades correctes.");
				}
				else 
					System.out.println("Si us plau, escriu les coordenades correctes.");
			}
			else
				System.out.println("Si us plau, escriu les coordenades correctes.");
		}
		return aux;
	}

	private static void posarFitxa(Coord posicio, int[][] tauler, int torn) {
		
		if(torn%2 == 0)
			tauler[posicio.X][posicio.Y] = 1;
		else
			tauler[posicio.X][posicio.Y] = 2;
	}

	private static Coord seleccionarPosicio(int[][] tauler, Jugador[] jugadors, int torn) {
		
		Coord aux = new Coord();
		while(true) {
			System.out.println("Tria una coordenada on posar la fitxa (x y): ");
			aux.X = ohowo.nextInt();
			aux.Y = ohowo.nextInt();
			if((aux.X >= 0 && aux.X < tauler.length && aux.Y >= 0 && aux.Y < tauler.length) && (tauler[aux.X][aux.Y] == 0)) {
				jugadors[torn%2].fitxes--;
				break;
			}
			else
				System.out.println("Si us plau, escriu les coordenades correctes.");
		}
		return aux;
	}

	private static void init(int[][] tauler, Jugador[] jugadors) {
		
		for(int i = 0; i < tauler.length; i++) {
			for(int j = 0; j < tauler[i].length; j++) {
				tauler[i][j] = 0;
			}
		}
		for(int i = 0; i < jugadors.length; i++) {
			System.out.println("Escriu el teu nom: ");
			jugadors[i] = new Jugador();
			jugadors[i].Nom = ohowo.nextLine();
		}
	}
}
