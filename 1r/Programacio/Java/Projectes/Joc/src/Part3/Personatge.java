package Part3;

import Core.Field;
import Core.PhysicBody;
import Core.Sprite;
import Part1.Roca;

public class Personatge extends PhysicBody{
	
	private int aterra;
	private Projectil pium;
//	private static int maxProjectils = 10;

	public Personatge(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f, Projectil a) {
		super(name, x1, y1, x2, y2, angle, path, f);
		this.setConstantForce(0,0.2);
		this.pium = a;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCollisionEnter(Sprite sprite) {
		// TODO Auto-generated method stub
		if(sprite instanceof Roca)
			this.aterra = 2;
		
		if(sprite instanceof Enemic) {
			if(this.x1 <= ((Enemic) sprite).x1 - 50)
				this.delete();
			if(this.x2 >= ((Enemic) sprite).x1 + 50)
				this.delete();
		}
		
		if(sprite instanceof Obstacle)
			if(sprite.name.equals("Pinxo"))
				this.delete();
	}

	@Override
	public void onCollisionExit(Sprite sprite) {
		// TODO Auto-generated method stub
	}
	
	public void disparar() {
		if(!this.delete) {
			this.pium = new Projectil(this.pium, (int) this.x1, (int) this.y1, (int) this.x2, (int) this.y2);
			this.pium.setVelocity(4, 0);
		}
	}
	
	public void moviment(Input3 in) {
		
		switch(in) {
			case SALT:
				if(aterra > 0) {
					this.setForce(0, -1.3);
					this.aterra--;
				}
				break;
			case ESQUERRA:
				this.setVelocity(-3, this.velocity[1]);
				break;
			case DRETA: 
				this.setVelocity(3, this.velocity[1]);
				break;
			case QUIET:
				this.setVelocity(0, 0);
				break;
			case DISPARAR:
				this.disparar();
				break;
			default:
				break;
		}
	}
}
