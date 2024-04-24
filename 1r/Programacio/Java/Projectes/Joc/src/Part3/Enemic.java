package Part3;

import Core.Field;
import Core.PhysicBody;
import Core.Sprite;

import Part1.Roca;

public class Enemic extends PhysicBody implements Disparable{

	private int vida;
	
	public Enemic(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f, int vida) {
		super(name, x1, y1, x2, y2, angle, path, f);
		this.vida = vida;
		this.setConstantForce(0,0.2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCollisionEnter(Sprite sprite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCollisionExit(Sprite sprite) {
		// TODO Auto-generated method stub
			if(sprite instanceof Roca) {
				if(this.x1 <= -200)
					this.delete();
			}
	}

	@Override
	public void danyar() {
		
		if(this.vida > 1)
			this.vida--;
		else 
			this.delete();
	}
	
	public void moure(Input3 in) {
		
		switch(in) {
		case ESQUERRA:
			this.setVelocity(-3, 0);
			break;
		case DRETA: 
			this.setVelocity(3, 0);
			break;
//		case DISPARAR:
//			this.disparar();
//			break;
		default:
			break;
		}
	}
}
