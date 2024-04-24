package TestsField;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Core.Field;
import Core.Sprite;
import Core.Window;

public class TestColisiones {

	// static ArrayList<Murciano> murcianos = new ArrayList<>();
	static int cont=0;
	static Field f = new Field();
	static Window w = new Window(f);
	static MovableChar c = new MovableChar("Char", 20, 20, 50, 50, "resources/link1.gif");
	static Rock b = new Rock("Rock", 150, 150, 250, 250, 0, "resources/rock2.png");
	static Rock b2 = new Rock("RotatedRock", 350, 150, 450, 250, 45, "resources/rock2.png");
	// static Davilillo d = new Davilillo("Davilillo", 350, 300, 400, 350,
	// "davi.gif");
	static ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public static void main(String[] args) throws InterruptedException {
		int contador = 0;
		boolean flag = false;
		while (!flag) {
			c.update();
			input();
			ArrayList<Sprite> sprites = new ArrayList<Sprite>();
			sprites.add(c);
			sprites.add(b);
			sprites.add(b2);
			f.draw(sprites);

			Thread.sleep(20);

		}
		System.out.println("estas en el lobby");

	}

	private static void input() {
		// TODO Auto-generated method stub
		// devuelve un set con todas las teclas apretadas
		if (w.getPressedKeys().contains('a') || w.getPressedKeys().contains('d') || w.getPressedKeys().contains('w')
				|| w.getPressedKeys().contains('s')) {
			if (c.firstCollidesWithField(f) != null) {
				cont++;
				System.out.println("He colisionado con "+c.firstCollidesWithField(f).name+" numero de colision: "+cont);
				System.out.println("porcentaje de colision "+c.collidesWithPercent(c.firstCollidesWithField(f)));
			}
			if (w.getPressedKeys().contains('a')) {
				c.moveIzq();

			}
			if (w.getPressedKeys().contains('d')) {
				c.moveDer();

			}
			if (w.getPressedKeys().contains('w')) {
				c.moveArr();

			}
			if (w.getPressedKeys().contains('s')) {
				c.moveAba();

			}

		}

	}

}
