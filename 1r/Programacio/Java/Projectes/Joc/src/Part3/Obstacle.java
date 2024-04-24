package Part3;

import Core.Field;
import Core.PhysicBody;
import Core.Sprite;

public class Obstacle extends PhysicBody implements Disparable{

	public Obstacle(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f) {
		super(name, x1, y1, x2, y2, angle, path, f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCollisionEnter(Sprite sprite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCollisionExit(Sprite sprite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void danyar() {
		// TODO Auto-generated method stub
		this.delete();
	}

}
