package Part2;

import Core.Field;
import Core.PhysicBody;
import Core.Sprite;
import Part1.Roca;

public class Personatge extends PhysicBody{
	
	private int aterra;

	public Personatge(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f) {
		super(name, x1, y1, x2, y2, angle, path, f);
		this.setConstantForce(0,0.2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCollisionEnter(Sprite sprite) {
		// TODO Auto-generated method stub
		if(sprite instanceof Roca)
			this.aterra = 2;
	}

	@Override
	public void onCollisionExit(Sprite sprite) {
		// TODO Auto-generated method stub
	}
	
	public void moviment(Input2 in) {
		
		switch(in) {
			case SALT:
				if(aterra > 0) {
					this.setForce(0, -1.3);
					this.aterra--;
				}
				break;
			case ESQUERRA:
				this.setVelocity(-1, 0);
				break;
			case DRETA: 
				this.setVelocity(1, 0);
				break;
			default:
				break;
		}
	}
}
