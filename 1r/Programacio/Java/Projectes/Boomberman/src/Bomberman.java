import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import Core.Board;
import Core.Window;

/** 
 * Clàssic joc de Boomberman creat en java amb un taulell bàsic proporcionat pel <a href="https://gitlab.com/malbareda/GraphicBoard">Marc Albareda</a>
 * @author Marc Sánchez
 * @version 1.0
 */
public class Bomberman {
	
	static Scanner sc = new Scanner(System.in);
	static Board t = new Board();
	static Window f = new Window(t);
	static int bombMax = 2;
	static int maxBomb = 2;
	static int power = 1;
	
	/**
	 * Funció principal amb el bucle del joc, on es criden totes les funcions secundàries 
	 * del mateix i inicialitzem el tauler amb una matriu predefinida i la resta d'atributs necessaris. 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		
		t.setActborder(false);
		t.setColorbackground(0xFFFFFF);
		
		int[][] matriz = { 
				{1,1,1,1,1,1,1},
				{1,4,0,3,0,0,1},
				{1,0,2,0,2,0,1},
				{1,3,0,3,0,3,1},
				{1,0,2,0,2,0,1},
				{1,0,0,3,0,5,1},
				{1,1,1,1,1,1,1}
			
		}; //Matriu predefinida, on els 1 i 2 son parets indestructibles, 3 son blocs destructibles, 4 es el Boomberman i el 5 l'enemic.
		
		int[][] matrizBombas = new int[7][7]; //Matriu de bombes. S'nicializa a 0 així
		Posicio pbomberman = new Posicio(1,1); //Inicialitzem el bomberman a la pos 1,1
		Posicio enemic = new Posicio(5,5);
		int num = 0; // Detectarem amb un 1 si ens hem tocat o amb un 2 si hem tocat a l'enemic.
		//init(matriz);
		matriz[pbomberman.f][pbomberman.c]=4;
		//printMatriz(matriz);
		int count = 0;
		
		while(true) {
			count++;
			Thread.sleep(100); //Impedim que el joc pugui executar-se el més ràpid possible per evitar possibles problemes. Amb aquest sleep estem fent que el codi vagi a 1000/100 fps -> 10 fps
			Set<Character> instruccio = f.getKeysDown();
			Set<Character> a = new HashSet<Character>(); 
			pbomberman = moure(matriz, matrizBombas, pbomberman.f,pbomberman.c, instruccio);
			
			if(instruccio.contains('z')) { //Cliquem la 'z' per a posar una bomba
				addBomba(pbomberman, matrizBombas);
			}
			if(count%4 == 0) {
				a.add(movimentEnemic());
				enemic = moure(matriz, matrizBombas, enemic.f, enemic.c, a);
				netejar(matrizBombas, matriz);
				num = tick(matrizBombas, matriz);
			}
			if(num == 1) {
				System.out.println("Oh no! Has mort :(");
				printMatrizGrafica(matriz,matrizBombas,t,f);
				break;
			}
			if(num == 2) {
				System.out.println("Has guanyat! Felicitats.");
				printMatrizGrafica(matriz,matrizBombas,t,f);
				break;
			}
			//printMatriz(matriz);
			printMatrizGrafica(matriz,matrizBombas,t,f);
		}
	}
	
	/**
	 * Retorna un moviment aleatori per a l'enemic d'entre quatre moviments possibles.
	 * @return moviment Moviment de l'enemic
	 */
	private static Character movimentEnemic() {
		Random r = new Random();
		int mov = r.nextInt(4);
		char moviment = '\0';
		switch(mov) {
		case 0:
			moviment = 'w';
			break;
		case 1:
			moviment = 'a';
			break;
		case 2:
			moviment = 's';
			break;
		case 3:
			moviment = 'd';
			break;
		}
		return moviment;
	}

	/**
	 * Afegeix una bomba en la matriu de bombes en la posició actual del personatge.
	 * @param pbomberman Posició actual del personatge
	 * @param matrizBombas Matriu on es col·loquen totes les bombes
	 */
	private static void addBomba(Posicio pbomberman, int[][] matrizBombas) {
		
		if(bombMax > 0) {
			matrizBombas[pbomberman.f][pbomberman.c]=16;
			bombMax--;
		}
	}
	
	/**
	 * Tick del joc (cada 40 fps) que va descontant el temps de les bombes existents en el tauler.
	 * @param matrizBombas Matriu on es col·loquen totes les bombes
	 * @param matriz Matriu del joc actual
	 * @return mort int amb el que sabrem si ens hem suicidat o si hem matat a l'enemic
	 */
	private static int tick(int[][] matrizBombas, int[][] matriz) {
		
		int mort = 0;
		for(int i = 0; i < matrizBombas.length; i++) {
			for(int j = 0; j < matrizBombas[i].length; j++) {
				if(matrizBombas[i][j] > 10)
					matrizBombas[i][j]--;
				else if(matrizBombas[i][j] == 10)
					mort = detonar(matrizBombas, matriz, i, j);
			}
		}
		return mort;
	}
	
	/**
	 * Detonem la bomba en les 4 direccions (comprovem cada direcció per si es possible, si ho és canviem el seu valor tant en la matriu de les bombes com en la matriu
	 * del joc) però només en una casella cada una.
	 * Si el poder es 2 augmentem el rang d'explosió 1 casella més en cada direcció.
	 * Si una bomba explota un bloc de totxo hi ha una probabilitat del 10% que et pugui el poder en +1 o bé que puguis tirar una bomba de més. 
	 * Només pots tenir un d'aquests poders actius alhora.
	 * @param matrizBombas Matriu on es col·loquen totes les bombes
	 * @param matriz Matriu del joc actual
	 * @param i Posició en les files de la bomba original
	 * @param j Posició en les columnes de la bomba original
	 * @return int Detectarem si alguna de les explosions ens ha tocat o ha tocat a l'enemic
	 */
	private static int detonar(int[][] matrizBombas, int[][] matriz, int i, int j) {
		
		// Mateixa posició on posem la bomba
		matrizBombas[i][j] = 9;
		if(matriz[i][j] == 4) {
			matriz[i][j] = 9;
			return 1;
		}
		if(matriz[i][j] >= 5 && matriz[i][j] <= 8) {
			matriz[i][j] = 9;
			return 2;
		}
		// Posició cap a dalt
		if(i > 1) {
			if(matrizBombas[i-1][j] >= 10) {
				detonar(matrizBombas, matriz, i-1, j);
			}
			else if(matriz[i-1][j] == 0 || matriz[i-1][j] == 3 || matriz[i-1][j] > 8) {
				if(matriz[i-1][j] == 3 && power == 1 && maxBomb == 2) {
					Random r = new Random();
					if(r.nextInt(10) == 5) {
						power++;
						System.out.println("Ara el teu poder es el doble!");
					}
					if(r.nextInt(10) == 2) {
						bombMax++;
						maxBomb++;
						System.out.println("Ara tens una bomba de més!");
					}
				}
				matrizBombas[i-1][j] = 9;
				matriz[i-1][j] = 9;
			}
			if(matriz[i-1][j] == 4) {
				matriz[i-1][j] = 9;
				return 1;
			}
			if(matriz[i-1][j] >= 5 && matriz[i-1][j] <= 8) {
				matriz[i-1][j] = 9;
				return 2;
			}
		}
		if(i > 2 && power == 2) {
			if(matrizBombas[i-2][j] >= 10) {
				detonar(matrizBombas, matriz, i-2, j);
			}
			else if((matriz[i-2][j] == 0 || matriz[i-2][j] == 3 || matriz[i-2][j] > 8) && matriz[i-1][j] >= 3) {
				matrizBombas[i-2][j] = 9;
				matriz[i-2][j] = 9;
			}
			if(matriz[i-2][j] == 4) {
				matriz[i-2][j] = 9;
				return 1;
			}
			if(matriz[i-2][j] >= 5 && matriz[i-2][j] <= 8) {
				matriz[i-2][j] = 9;
				return 2;
			}
		}
		//Posició cap a l'esquerra
		if(j > 1) {
			if(matrizBombas[i][j-1] >= 10) {
				detonar(matrizBombas, matriz, i, j-1);
			}
			else if(matriz[i][j-1] == 0 || matriz[i][j-1] == 3 || matriz[i][j-1] > 8) {
				if(matriz[i][j-1] == 3 && power == 1 && maxBomb == 2) {
					Random r = new Random();
					if(r.nextInt(10) == 5) {
						power++;
						System.out.println("Ara el teu poder es el doble!");
					}
					if(r.nextInt(10) == 2) {
						bombMax++;
						maxBomb++;
						System.out.println("Ara tens una bomba de més!");
					}
				}
				matrizBombas[i][j-1] = 9;
				matriz[i][j-1] = 9;
			}
			if(matriz[i][j-1] == 4) {
				matriz[i][j-1] = 9;
				return 1;
			}
			if(matriz[i][j-1] >= 5 && matriz[i][j-1] <= 8) {
				matriz[i][j-1] = 9;
				return 2;
			}
		}
		if(j > 2 && power == 2) {
			if(matrizBombas[i][j-2] >= 10) {
				detonar(matrizBombas, matriz, i, j-2);
			}
			else if((matriz[i][j-2] == 0 || matriz[i][j-2] == 3 || matriz[i][j-2] > 8) && matriz[i][j-1] >= 3) {
				matrizBombas[i][j-2] = 9;
				matriz[i][j-2] = 9;
			}
			if(matriz[i][j-2] == 4) {
				matriz[i][j-2] = 9;
				return 1;
			}
			if(matriz[i][j-2] >= 5 && matriz[i][j-2] <= 8) {
				matriz[i][j-2] = 9;
				return 2;
			}
		}
		//Posició cap a la dreta
		if(j < matrizBombas.length - 2) {
			if(matrizBombas[i][j+1] >= 10) {
				detonar(matrizBombas, matriz, i, j+1);
			}
			else if(matriz[i][j+1] == 0 || matriz[i][j+1] == 3 || matriz[i][j+1] > 8) {
				if(matriz[i][j+1] == 3 && power == 1 && maxBomb == 2) {
					Random r = new Random();
					if(r.nextInt(10) == 5) {
						power++;
						System.out.println("Ara el teu poder es el doble!");
					}
					if(r.nextInt(10) == 2) {
						bombMax++;
						maxBomb++;
						System.out.println("Ara tens una bomba de més!");
					}
				}
				matrizBombas[i][j+1] = 9;
				matriz[i][j+1] = 9;
			}
			if(matriz[i][j+1] == 4) {
				matriz[i][j+1] = 9;
				return 1;
			}
			if(matriz[i][j+1] >= 5 && matriz[i][j+1] <= 8) {
				matriz[i][j+1] = 9;
				return 2;
			}
		}
		if(j < matrizBombas.length - 3 && power == 2) {
			if(matrizBombas[i][j+2] >= 10) {
				detonar(matrizBombas, matriz, i, j+2);
			}
			else if((matriz[i][j+2] == 0 || matriz[i][j+2] == 3 || matriz[i][j+2] > 8) && matriz[i][j+1] >= 3) {
				matrizBombas[i][j+2] = 9;
				matriz[i][j+2] = 9;
			}
			if(matriz[i][j+2] == 4) {
				matriz[i][j+2] = 9;
				return 1;
			}
			if(matriz[i][j+2] >= 5 && matriz[i][j+2] <= 8) {
				matriz[i][j+2] = 9;
				return 2;
			}
		}
		//Posició cap a baix
		if(i < matrizBombas.length - 2) {
			if(matrizBombas[i+1][j] >= 10) {
				detonar(matrizBombas, matriz, i+1, j);
			}
			else if(matriz[i+1][j] == 0 || matriz[i+1][j] == 3 || matriz[i+1][j] > 8) {
				if(matriz[i+1][j] == 3 && power == 1 && maxBomb == 2) {
					Random r = new Random();
					if(r.nextInt(10) == 5) {
						power++;
						System.out.println("Ara el teu poder es el doble!");
					}
					if(r.nextInt(10) == 2) {
						bombMax++;
						maxBomb++;
						System.out.println("Ara tens una bomba de més!");
					}
				}
				matrizBombas[i+1][j] = 9;
				matriz[i+1][j] = 9;
			}
			if(matriz[i+1][j] == 4) {
				matriz[i+1][j] = 9;
				return 1;
			}
			if(matriz[i+1][j] >= 5 && matriz[i+1][j] <= 8) {
				matriz[i+1][j] = 9;
				return 2;
			}
		}
		if(i < matrizBombas.length - 3 && power == 2) {
			if(matrizBombas[i+2][j] >= 10) {
				detonar(matrizBombas, matriz, i+2, j);
			}
			else if((matriz[i+2][j] == 0 || matriz[i+2][j] == 3 || matriz[i+2][j] > 8) && matriz[i+1][j] >= 3) {
				matrizBombas[i+2][j] = 9;
				matriz[i+2][j] = 9;
			}
			if(matriz[i+2][j] == 4) {
				matriz[i+2][j] = 9;
				return 1;
			}
			if(matriz[i+2][j] >= 5 && matriz[i+2][j] <= 8) {
				matriz[i+2][j] = 9;
				return 2;
			}
		}
		bombMax++;
		return 0;
	}
	
	/**
	 * Netegem el taulell de les explosions de la funció {@link #detonar(int[][], int[][], int, int) detonar}
	 * @param matrizBombas Matriu on es col·loquen totes les bombes
	 * @param matriz Matriu del joc actual
	 */
	private static void netejar(int[][] matrizBombas, int[][] matriz) {
		
		for(int i = 0; i < matrizBombas.length; i++) {
			for(int j = 0; j < matrizBombas[i].length; j++) {
				if(matrizBombas[i][j] == 9 || matriz[i][j] == 9) {
					matrizBombas[i][j] = 0;
					matriz[i][j] = 0;
				}
			}
		}
	}

	/**
	 * Comprovem que no ens sortim del taulell i quina de les tecles estem pressionant per a moure el bomberman o l'enemic.
	 * @param matriz Matriu del joc actual 
	 * @param matrizBombas Matriu de les bombes actives
	 * @param h Posició actual del personatge/enemic en les files
	 * @param v Posició actual del personatge/enemic en les columnes
	 * @param instruccio Tecla que hem presionat en el teclat
	 * @return p Noves coordenades amb el moviment del bomberman o de l'enemic
	 */
	private static Posicio moure(int[][] matriz, int[][] matrizBombas, int h, int v, Set<Character> instruccio) {
		
		int pj = matriz[h][v];
		if(instruccio.contains('w')) {
			matriz[h][v] = 0;
			
			h--;
			if(meSalgo(h,v,matriz) || matriz[h][v]!=0 || matrizBombas[h][v]!=0) {
				h++;
				matriz[h][v] = pj;
			}else {					
				matriz[h][v] = pj;
			}
			
		}
		else if(instruccio.contains('d')) {
			matriz[h][v] = 0;
			
			v++;
			if(meSalgo(h,v,matriz) || matriz[h][v]!=0 || matrizBombas[h][v]!=0) {
				v--;
				matriz[h][v] = pj;
			}else {

				matriz[h][v] = pj;
			}
			
		}
		else if(instruccio.contains('a')) {
			matriz[h][v] = 0;
			
			v--;
			if(meSalgo(h,v,matriz)  || matriz[h][v]!=0 || matrizBombas[h][v]!=0) {
				v++;
				matriz[h][v] = pj;
			}else {

				matriz[h][v] = pj;
			}
			
		}
		else if(instruccio.contains('s')) {
			matriz[h][v] = 0;
			
			h++;
			if(meSalgo(h,v,matriz) || matriz[h][v]!=0 || matrizBombas[h][v]!=0) {
				h--;
				matriz[h][v] = pj;
			}else {

				matriz[h][v] = pj;
			}
			
		}
		Posicio p = new Posicio(h, v);
		return p;
	}

	/**
	 * Canviem les coordenades de la amtriu per sprites específics per a cada nombre, en aquest cas: paret, totxo, bomberman, enemic, bomba i explosió.
	 * @param matriz
	 * @param matrizBombas
	 * @param t
	 * @param f
	 */
	private static void printMatrizGrafica(int[][] matriz, int[][] matrizBombas, Board t, Window f) {
		
		String[] imatges = { "", "p1.png", "p1.png", "t1.png", "b1.png", "g1.png", "g1.png",
				"g1.png", "g1.png", "e.png","e.png","b2.png","b2.png","b2.png","b2.png","b2.png","b2.png","b2.png" };
		//0 nada, 1-2, pared, 3 bloque, 4 bomberman, 5-8 enemigo, 9-10 explosion, 11-20 bomba
		t.setSprites(imatges);
		int[][][] cosa = new int[2][7][7];
		cosa[0] = matriz;
		cosa[1] =matrizBombas;
		t.draw(cosa);
	}

	/**
	 * Inicialitzem la matriu
	 * @deprecated
	 * Funció inutilitzada. Ja s'inicialitza la matriu en el main.
	 * 
	 * @param matriz
	 */
	/* private static void init(int[][]matriz) {
		// TODO Auto-generated method stub
		
	} */ 
	
	/**
	 * Mostrem la matriu per pantalla
	 * @deprecated
	 * Ja estem mostrant la matriu amb la finestra interactiva.
	 * 
	 * @param matriz
	 */
	/*private static void printMatriz(int[][] matriz) {
		
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				System.out.print(matriz[i][j]+" ");
			} System.out.println();
		}
	}*/

	/**
	 * Mirem si les coordenades es surten dels límits de la matriu o no.
	 * @param h
	 * @param v
	 * @param matriz
	 * @return
	 */
	public static boolean meSalgo(int h, int v, int[][] matriz){
		
		if(h<0||v<0||h>=matriz.length||v>=matriz[0].length) {
			return true;
		}else {
			return false;
		}
	}
}
