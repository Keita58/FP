package TestsField;

import java.util.ArrayList;

import Core.Field;
import Core.Sprite;
import Core.Window;

public class TestFighter {
	
	
	public static Field f = new Field();
	public static Window w = new Window(f);
	public static Ganso ganso = new Ganso("Honk", 50, 50, 130, 170, 0, "resources/ganso.png");
	public static Suelo suelo = new Suelo("Suelo", 0, 250, 3000, 300, "resources/joan.jpg");
	public static Institut ies = new Institut("Instiut", 300, 50, 500, 150, 0, "resources/institut.jpg");
	public static ArrayList<Hitbox> listHitbox = new ArrayList<Hitbox>();
	
	public static void main(String[] args) throws InterruptedException {
		f.background="rainb.jpg";
		ganso.addConstantForce(0, 0.2);
		ies.addConstantForce(0, 0.4);
		
		while(true) {
			
			ArrayList<Sprite> sprites = new ArrayList<Sprite>();
			sprites.add(ganso);
			sprites.add(suelo);
			sprites.add(ies);
			sprites.addAll(listHitbox);
			input();
			for (Hitbox h : listHitbox) {
				h.frame();
			}
			if(ganso.isGrounded(f)) {
				//c.getGrounded(f);
				//System.out.println("grounded");
				ganso.aterra=true;
			}
			f.draw(sprites);
			//60 fps
			Thread.sleep(30);
			
			
		}
		
		
		
		
	}
	
	private static void input() {
		// TODO Auto-generated method stub
		if(w.getPressedKeys().contains('w')) {
			ganso.jump();
		}
		if(w.getPressedKeys().contains('d')) {
			ganso.moveRight();
		}if(w.getPressedKeys().contains('a')) {
			ganso.moveLeft();
		}
		if(!w.getPressedKeys().contains('d')&&!w.getPressedKeys().contains('a')) {
			ganso.doNotMove();
		}
		if(w.getKeysDown().contains(' ')) {
			w.playSFX("honk.wav");
			Hitbox h = ganso.pegar();
			h.comprobarColision(ies);
			listHitbox.add(h);
			
		}
		
	}

}
