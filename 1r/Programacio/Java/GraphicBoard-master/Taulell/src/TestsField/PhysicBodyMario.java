package TestsField;

import Core.Field;
import Core.Sprite;

public class PhysicBodyMario extends Sprite {
	
	int status = 0;
	
	public PhysicBodyMario(String name, int x1, int y1, int x2, int y2, String path) {
		super(name, x1, y1, x2, y2, path);
		this.physicBody=true;
		// TODO Auto-generated constructor stub
	}
	
	public void move(char c) {
		// TODO Auto-generated method stub
		if (c == 'd') {
			this.setVelocity(5, 0);
		} else if (c == 'a') {
			this.setVelocity(-5, 0);
		}
	}
	
	public void fall(Field f) {
		if (isGrounded(f)) {
			//System.out.println("POF");
			status = 0;
			getGrounded(f);

		}
	}


	public void jump() {
		// TODO Auto-generated method stub
		
		if (status == 0) {
			status = 1;
			this.addForce(0, -5);
			

		}

	}

}
