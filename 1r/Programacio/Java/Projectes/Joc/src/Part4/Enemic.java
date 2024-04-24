package Part4;

import java.util.Timer;
import java.util.TimerTask;

import Core.Field;
import Core.PhysicBody;
import Core.Sprite;

public class Enemic extends PhysicBody{
	
	Timer timer = new Timer();

	public Enemic(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f) {
		super(name, x1, y1, x2, y2, angle, path, f);
		timer.schedule(task1, 0, 500);
		this.layer = 2;
		this.ignoredLayers.add(2);
		this.ignoredLayers.add(3);
		this.ignoredLayers.add(4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCollisionEnter(Sprite sprite) {
		if(sprite instanceof Nau) {
			this.delete();	
			UI.instance.vida.perdVida();
		}
	}

	@Override
	public void onCollisionExit(Sprite sprite) {
		// TODO Auto-generated method stub
		
	}
	
	public void eliminar() {
		// TODO Auto-generated method stub
		if(this.y1 > 800){
	        this.delete();
	    }
	}
	
	TimerTask task1 = new TimerTask() {
        //la funció run indica tot allò que volguem executar
        @Override
        public void run()
        {
        	eliminar();
        }
    };
    
}
