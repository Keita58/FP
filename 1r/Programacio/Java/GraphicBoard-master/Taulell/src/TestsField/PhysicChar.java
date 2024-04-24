package TestsField;

import Core.Sprite;

public class PhysicChar extends Sprite {

	boolean aterra=false;
	public PhysicChar(String name, int x1, int y1, int x2, int y2,String path) {
		super(name, x1, y1, x2, y2, path);
		physicBody = true;
		// TODO Auto-generated constructor stub
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

	public Mandarina shoot() {
		// TODO Auto-generated method stub
		Mandarina m = new Mandarina("Mandarina", x1+60, y1, x2+60, y2, 0, "resources/mandarina.png");
		m.move();
		return m;
		
	}
	


}
