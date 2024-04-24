package TestsField;

import java.util.ArrayList;

import Core.Field;
import Core.Sprite;
import Core.Window;

public class TestPhysicBody {
	
	static int cont=0;
	static Field f = new Field();
	static Window w = new Window(f);
	static Rock c = new Rock("Char", 220, 220, 250, 250, 0, "resources/link1.gif");
	static Suelo floor = new Suelo("Char", 0, 300, 2500, 350, "resources/rock1.png");
	
	public static void main(String[] args) throws InterruptedException {
		
		int contador = 0;
		boolean flag = false;
		c.setConstantForce(0, 0.2);
		c.addForce(0, -2);
		c.setVelocity(5, 0);
		
		
		while (!flag) {
			ArrayList<Sprite> sprites = new ArrayList<Sprite>();
			sprites.add(c);
			sprites.add(floor);
			f.draw(sprites);
			Thread.sleep(20);

		}
		
	}

}
