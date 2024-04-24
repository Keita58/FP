package TestsField;

import Core.Sprite;

public class Ganso extends Sprite{

	boolean aterra=false;

	public Ganso(String name, int x1, int y1, int x2, int y2, double angle, String path) {
		super(name, x1, y1, x2, y2, angle, "resources/ganso.png");
		// TODO Auto-generated constructor stub
		this.physicBody=true;
		this.terrain=false;
		this.trigger=false;
	}
	
	public void jump() {
		//System.out.println(aterra);
		if(aterra==true) {
			//System.out.println("jump "+aterra);
			setForce(0, -2);
			aterra = false;
		}
		
	}
	
	public void moveRight() {
		//x1+=3;
		//x2+=3;
		if(aterra) {
			setVelocity(2, velocity[1]);
		}
		
	}
	
	public void moveLeft() {
		//x1-=3;
		//x2-=3;
		if(aterra) {
			setVelocity(-2, velocity[1]);
		}
	}

	public void doNotMove() {
		// TODO Auto-generated method stub
		if(aterra) {
			setVelocity(0, velocity[1]);
		}
		
	}

	public Hitbox pegar() {
		Hitbox h = new Hitbox("hitbox", x2, (y1+y2)/2, x2+50, ((y1+y2)/2)+50, 0);
		return h;
	}
	
	
	
	

}
