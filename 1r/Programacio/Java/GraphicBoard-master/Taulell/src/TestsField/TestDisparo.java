package TestsField;

import java.util.ArrayList;

import Core.Field;
import Core.Sprite;
import Core.Window;

public class TestDisparo {
	
	//declaracio de field
	static Field f = new Field();
	//declracio de window
	static Window w = new Window(f);
	
	//declarem un personatge
	static PhysicChar c = new PhysicChar("Char", 220, 220, 250, 250,  "resources/link1.gif");
	//declarem un terra
	static Suelo floor = new Suelo("Floor", 0, 300, 2500, 350, "resources/rock1.png");
	static Suelo platform = new Suelo("platform", 500, 180, 600, 190, "resources/rock1.png");
	static ArrayList<Mandarina> m = new ArrayList<Mandarina>();
	static Joan joan = new Joan("",800, 220, 850, 250, 0, "");
	
public static void main(String[] args) throws InterruptedException {
		
		//floor.physicBody = false;
		
		boolean flag = false;
		//posem una força constant, com la gravetat
		c.setConstantForce(0, 0.2);
		//posem una força inmediata, que serà modificada per la força constant
		//c.addForce(0, -0.2);
		//posem una velocitat constant
		//c.setVelocity(5, 0);
		
		//els objectes es mouran per els seus vectors de força i velocitat fins que colisionin amb un altre
		//physicbody. Després perdran tots els seus vectors.
		
		while (!flag) {
			ArrayList<Sprite> sprites = new ArrayList<Sprite>();
			sprites.add(c);
			sprites.add(floor);
			sprites.add(platform);
			//sprites.add(m);
			sprites.addAll(m);
			sprites.add(joan);

			disparos();
			
			if(c.isGrounded(f)) {
				//c.getGrounded(f);
				//System.out.println("aterra");
				c.aterra=true;
			}
			input();
			f.draw(sprites);
			Thread.sleep(30);

		}
		
	}

	private static void disparos() {
	// TODO Auto-generated method stub
		for(Mandarina man : m) {
			man.collision();
		}
	}

	private static void input() {
		// TODO Auto-generated method stub
		if(w.getPressedKeys().contains('w')) {
			c.jump();
		}
		if(w.getPressedKeys().contains('d')) {
			c.moveRight();
		}if(w.getPressedKeys().contains('a')) {
			c.moveLeft();
		}
		if(!w.getPressedKeys().contains('d')&&!w.getPressedKeys().contains('a')) {
			c.doNotMove();
		}
		if(w.getKeysDown().contains(' ')) {
			m.add(c.shoot());
			
		}
		
	}

}
