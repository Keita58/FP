package TestsField;

import java.awt.Color;

import Core.Sprite;

public class Hitbox extends Sprite{

	int frames = 10;
	public Hitbox(String name, int x1, int y1, int x2, int y2, double angle) {
		super(name, x1, y1, x2, y2, angle, new Color(255, 0, 0, 150));
		// TODO Auto-generated constructor stub
		this.physicBody=true;
		this.terrain=false;
		this.trigger=true;
	}
	public void frame() {
		// TODO Auto-generated method stub
		frames-=1;
		if(frames==0) {
			this.delete();
		}
		
	}
	
	public void comprobarColision(Institut otro) {
		if(this.collidesWith(otro)){
			
			otro.daño+=10;
			System.out.println("pum "+otro.daño);
			otro.addForce(0, -0.1*otro.daño);
		}
	}
	
	

}
