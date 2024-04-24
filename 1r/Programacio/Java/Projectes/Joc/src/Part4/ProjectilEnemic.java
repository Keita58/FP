package Part4;

import Core.Field;
import Core.PhysicBody;
import Core.Sprite;

public class ProjectilEnemic extends PhysicBody{
	
	private int xV2;

	public ProjectilEnemic(ProjectilEnemic p, int x1, int y1, int x2, int y2) {
		super(p.name, x1, y1, x2, y2, p.angle, p.path, p.f);
		this.trigger = true;
		this.xV2 = x1 + 500;
		this.layer = 3;
		this.ignoredLayers.add(-10);
	}
	
	public ProjectilEnemic(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f) {
		super(name, x1, y1, x2, y2, angle, path, f);
		this.trigger = true;
		this.xV2 = x1 + 500;
	}

	@Override
	public void onCollisionEnter(Sprite sprite) {
		System.out.println("coll with " + sprite.name); //imprimeix objecte si ha colisionat
        if(sprite instanceof Disparable) { //es pot fer un instanceof a l'interfície, no només la classe
            Disparable d = (Disparable) sprite; //casteig a disparable
            d.danyarEnemic(); //cridem a danyar
            System.out.println("pum");
            this.delete(); //esborrem el projectil
        }
	}

	@Override
	public void onCollisionExit(Sprite sprite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(this.y1 > 800)
			this.delete();
	}
}
