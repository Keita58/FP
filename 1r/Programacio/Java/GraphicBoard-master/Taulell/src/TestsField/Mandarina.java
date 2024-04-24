package TestsField;

import Core.Sprite;

public class Mandarina extends Sprite {

	public Mandarina(String name, int x1, int y1, int x2, int y2, double angle, String path) {
		super(name, x1, y1, x2, y2, angle, path);
		this.physicBody=true;
		this.trigger=true;
		
	}
	
	public void move(){
		this.setVelocity(4, 0);
	}

	public void collision() {
		
		if(this.collidesWith(TestDisparo.joan)) {
			System.out.println("pum");
			this.delete();
		}
		
	}

}
