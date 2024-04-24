package TestsBoard;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

import Core.Board;
import Core.BoardSprite;
import Core.Sprite;
import Core.Window;
import TestsField.Texto;

/**
 * Test Petita demostraci� de representacions possibles. s'avan�a donant
 * enter a la consola
 *
 */

public class TestNewBoard {

	public static void main(String[] args) {
		Board b = new Board();
		Window f = new Window(b);

		Scanner sc = new Scanner(System.in);

		System.out.println("avança entre els casos apretant enter");
		sc.nextLine();

		// cas 1: Hundir la flota

		System.out.println("cas 1: Colors - Enfonsar la flota");

		int[] colors = { 0x0000FF, 0x00FF00, 0xFFFF00, 0xFF0000, 0xFF00FF, 0x00FFFF, 0x000000, 0xFFFFFF, 0xFF8000,
				0x7F00FF };
		b.setColors(colors);
		f.setActLabels(true); // les etiquetes i titol com que no tenen a
								// veure amb la matriu es tracten per la
								// finestra!
		String[] etiquetes = { "Dispars:10", "Portavions:1", "Cuirassats:0", "Creuers:0", "Submarins:1" };
		f.setLabels(etiquetes);
		f.setTitle("Enfonsar la flota");
		int[][] matriu = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 5, 3, 3, 0, 0, 5, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 5, 0, 6, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 6, 0, 5, 0, 0, 0 },
				{ 0, 0, 0, 0, 6, 0, 0, 0, 6, 0 }, { 0, 0, 5, 0, 6, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 2, 4, 1, 7, 8, 9 }, };

		b.draw(matriu, 'c');
		sc.nextLine();

		// cas 2: Buscaminas

		System.out.println("cas 2: Text - Buscamines");

		b.setColorbackground(0xb1adad);
		b.setActborder(true);
		String[] lletres = { "", "1", "2", "3", "4", "5", "6", "7", "8", "*" }; // qu�
																				// s'ha
																				// d'escriure
																				// en
																				// cada
																				// casella
																				// en
																				// base
																				// al
																				// nombre
		b.setText(lletres);
		int[] colorlletres = { 0x0000FF, 0x00FF00, 0xFFFF00, 0xFF0000, 0xFF00FF, 0x00FFFF, 0x521b98, 0xFFFFFF, 0xFF8000,
				0x7F00FF };
		b.setColortext(colorlletres);
		String[] etiquetes2 = { "Mines: 10", "Temps: 600" };
		f.setLabels(etiquetes2);
		f.setActLabels(true);
		f.setTitle("Cercamines");

		int[][] matriu2 = { { 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 }, { 9, 9, 1, 1, 2, 9, 9, 5, 9, 9 },
				{ 9, 9, 9, 9, 3, 9, 9, 9, 9, 9 }, { 9, 9, 5, 9, 6, 9, 9, 9, 9, 9 }, { 9, 9, 9, 9, 6, 9, 5, 9, 9, 9 },
				{ 9, 9, 8, 9, 6, 9, 9, 9, 6, 9 }, { 9, 9, 5, 9, 6, 9, 9, 9, 9, 9 }, { 1, 1, 1, 9, 9, 9, 9, 9, 9, 9 },
				{ 0, 0, 1, 2, 9, 9, 9, 9, 9, 9 }, { 0, 0, 0, 1, 9, 4, 1, 7, 8, 9 }, };

		b.draw(matriu2, 't');
		sc.nextLine();

		// cas 3: The Legend of Zelda

		System.out.println("cas 3: Imatges - The Legend of Zelda");
		b.setColorbackground(0xfed8a7);
		b.setActborder(false);
		String[] imatges = { "", "resources/Link1.gif", "resources/rock2.png", "resources/rock1.png", "resources/octorok.gif", "resources/octorok.gif", "resources/octorok.gif",
				"resources/octorok.gif", "resources/octorok.gif", "resources/octorok.gif" };
		b.setSprites(imatges);
		f.setActLabels(false);
		f.setTitle("The Legend of Zelda");

		int[][] matriu3 = { { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 }, { 0, 0, 0, 0, 0, 0, 0, 4, 0, 0 },
				{ 0, 0, 0, 3, 3, 0, 0, 0, 0, 0 }, { 0, 1, 0, 3, 3, 0, 4, 0, 0, 0 }, { 0, 0, 0, 0, 6, 0, 0, 0, 0, 0 },
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 }, };

		b.draw(matriu3);

		sc.nextLine();
		
		//cas 3.1 exemple de zoom
		
		System.out.println("cas 3.1: exemple de zoom");
		b.draw(matriu3,1,1,4,4);

		sc.nextLine();
		// cas 4: Exemple de background

		System.out.println("cas 4: Exemple de imgbackground");
		b.setActimgbackground(true);
		b.setImgbackground("resources/b84.png");
		b.draw(matriu3);

		sc.nextLine();

		// cas 5: Exemple d'overdraw

		System.out.println("cas 5: Exemple de multilayer");
		b.setActimgbackground(false);
		int[][][] matriulayer = { matriu3, { { 0, 1 } }, { { 6, 0 }, { 0, 6 } } };

		b.draw(matriulayer);
		sc.nextLine();

		// cas 5.1: Exemple d'overdraw amb sprites i text

		System.out.println("cas 5: Exemple de multilayer amb sprites i text");

		int[][][] matriulayer2 = { matriu3, { { 0, 1 } }, { { 1, 0 }, { 2, 3 } } };
		String[] frases = { "A", "B", "C", "D", "r", "5", "6", "7", "8", "*" }; // qu�

		char[] type = { 's', 's', 't' };
		b.setText(frases);
		b.draw(matriulayer2, type);
		sc.nextLine();

		/// cas 6: Comprovacio mouse i teclat

		f.setActLabels(false);
		//f.setDebugLabel(true);
		b.clearBoardSprites();

		System.out.println("cas 6: Comprovacio mouse i teclat");
		b.draw(matriu3);
		System.out.println(
				"pots provar de clicar la casella que vulguis o de apretar les tecles que vulguis i es mostraran (per mostrar la casella clicada tamb� has d'apretar una tecla qualsevol."
						+ "Prem enter quan acabis.");

		do {
			
			try {
				Thread.sleep(50);  ///donem una mica de retard per no colapsar el buffer del mouse.
			} catch (InterruptedException e) {} 
			
			int leftCol = b.getCurrentMouseCol();
			int leftRow = b.getCurrentMouseRow();
			if (leftCol != -1 && leftRow != -1) {
				System.out.println("S'ha apretat el bot� esquerra en la fila " + leftRow + " columna " + leftCol);
			}
			int rightCol = b.getCurrentRightMouseCol();
			int rightRow = b.getCurrentRightMouseRow();
			if (rightCol != -1 && rightRow != -1) {
				System.out.println("S'ha apretat el bot� dret la fila " + rightRow + " columna " + rightCol);
			}
			if (!f.getPressedKeys().isEmpty()) {
				System.out.println("Tecles apretades "+f.getPressedKeys());
			}
			

		} while (!f.getPressedKeys().contains('\n'));
		System.out.println("l'ultima casella clickada es:  fila " + b.getMouseRow() + "   columna: " + b.getMouseCol());
		System.out.println("l'ultima tecla premuda es:   " + f.getKeyPressed());
		System.out.println("Aquesta es la llista de totes les tecles que tenies apretades al premer l'enter:   "
				+ f.getPressedKeys());

		/// cas 7: Freedraw

		System.out.println("cas 7: Freedraw");
		f.setActLabels(false);
		f.setDebugLabel(false);

		b.setColorbackground(0xfed8a7);
		b.setActborder(false);
		b.setActfreedraw(true);
		String[] imatges2 = { "", "resources/Link1.gif", "resources/rock2.png", "resources/rock1.png", "resources/octorok.gif", "resources/octorok.gif", "resources/octorok.gif",
				"resources/octorok.gif", "resources/octorok.gif", "resources/octorok.gif" };
		double[] freedrawx = { 1, 1, 1, 1, 1.5, 1, 1, 1, 1, 1 };
		double[] freedrawy = { 1, 1, 1, 1, 2, 1, 1, 1, 1, 1 };
		b.setFreedrawx(freedrawx);
		b.setFreedrawy(freedrawy);
		b.setSprites(imatges2);
		f.setActLabels(false);
		f.setTitle("The Legend of Zelda");

		b.draw(matriu3);

		sc.nextLine();

		/// cas 7.1: Freeoverdraw

		System.out.println("cas 7: Freeoverdraw");
		f.setActLabels(false);
		f.setDebugLabel(false);

		b.setColorbackground(0xfed8a7);
		b.setActborder(false);
		b.setActfreedraw(true);
		String[] imatges3 = { "", "resources/Link1.gif", "resources/rock2.png", "resources/rock1.png", "resources/octorok.gif", "resources/octorok.gif", "resources/octorok.gif",
				"resources/octorok.gif", "resources/octorok.gif", "resources/octorok.gif" };
		double[] freedrawx2 = { 1, 1, 1, 1, 1.5, 1, 1, 1, 1, 1 };
		double[] freedrawy2 = { 1, 1, 1, 1, 2, 1, 1, 1, 1, 1 };
		b.setFreedrawx(freedrawx2);
		b.setFreedrawy(freedrawy2);
		b.setSprites(imatges3);
		f.setActLabels(false);
		f.setTitle("The Legend of Zelda");

		int[][] matriu6 = { { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 }, { 0, 0, 0, 0, 0, 0, 0, 4, 0, 0 },
				{ 0, 0, 0, 3, 3, 0, 0, 0, 0, 0 }, { 0, 1, 0, 3, 3, 0, 4, 0, 0, 0 }, { 0, 0, 0, 0, 6, 0, 0, 0, 0, 0 },
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 }, };

		b.draw(matriulayer);

		sc.nextLine();
		
		///cas 8 combinació de Board i Field (Field sobre board)
		
		System.out.println("cas 8: combinació de Board i Field (Field sobre board)\n" + 
				"");
		b.clearBoardSprites();
		b.setColorbackground(0xfed8a7);
		b.setActborder(false);
		String[] imatges9 = { "", "resources/Link1.gif", "resources/rock2.png", "resources/rock1.png", "resources/octorok.gif", "resources/octorok.gif", "resources/octorok.gif",
				"resources/octorok.gif", "resources/octorok.gif", "resources/octorok.gif" };
		b.setSprites(imatges9);
		f.setActLabels(false);
		f.setTitle("The Legend of Zelda");

		int[][] matriu9 = { { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 }, { 0, 0, 0, 0, 0, 0, 0, 4, 0, 0 },
				{ 0, 0, 0, 3, 3, 0, 0, 0, 0, 0 }, { 0, 1, 0, 3, 3, 0, 4, 0, 0, 0 }, { 0, 0, 0, 0, 6, 0, 0, 0, 0, 0 },
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 }, };
		
		BoardSprite txt1 = new BoardSprite("texto", 1, 1, 2, 2, "TEXT RANDOM");
		txt1.font=new Font("Comic Sans MS", Font.BOLD, 22);
		txt1.text=true;
		BoardSprite s = new BoardSprite("TextBox",3,0,5,9,"textbox.png");
		BoardSprite txt2 = new BoardSprite("texto", 4, 1, 5, 1, "TEXT A DINTRE DE LA TEXTBOX");
		txt2.text=true;
		ArrayList<BoardSprite> textos = new ArrayList<>();
		textos.add(txt1);
		textos.add(s);
		textos.add(txt2);
		b.setBoardSprites(textos);
		b.draw(matriu9);
		sc.nextLine();

		/// cas 9: Doble finestra

		System.out.println("cas 9: Doble finestra");
		Board t2 = new Board();
		Window f2 = new Window(b, t2);
		int[] colors2 = { 0x0000FF, 0x00FF00, 0xFFFF00, 0xFF0000, 0xFF00FF, 0x00FFFF, 0x000000, 0xFFFFFF, 0xFF8000,
				0x7F00FF };
		b.setColors(colors2);
		f2.setActLabels(true); // les etiquetes i titol com que no tenen a
								// veure amb la matriu es tracten per la
								// finestra!
		String[] etiquetes3 = { "Dispars:10", "Portavions:1", "Cuirassats:0", "Creuers:0", "Submarins:1" };
		f2.setLabels(etiquetes3);
		f2.setTitle("Enfonsar la flota");
		t2.setColors(colors2);

		int[][] matriu41 = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 5, 3, 3, 0, 0, 5, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 5, 0, 6, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 6, 0, 5, 0, 0, 0 },
				{ 0, 0, 0, 0, 6, 0, 0, 0, 6, 0 }, { 0, 0, 5, 0, 6, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 2, 4, 1, 7, 8, 9 }, };

		int[][] matriu42 = { { 0, 0, 0, 5, 0, 5, 0, 0, 0, 0 }, { 0, 0, 5, 3, 3, 0, 0, 5, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 5, 0, 6, 0, 0, 0, 0, 0 }, { 0, 0, 5, 0, 6, 0, 5, 0, 0, 0 },
				{ 0, 5, 0, 0, 6, 0, 0, 5, 6, 0 }, { 0, 0, 5, 0, 6, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 2, 4, 1, 7, 8, 9 }, };
		b.draw(matriu41, 'c');

		t2.draw(matriu42, 'c');
		
		sc.nextLine();
		


		


	}

}