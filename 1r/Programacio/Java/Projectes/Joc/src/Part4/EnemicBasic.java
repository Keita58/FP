package Part4;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Core.Field;
import Core.Sprite;

public class EnemicBasic extends Enemic implements Disparable{
	
	Timer timer = new Timer();

	public EnemicBasic(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f, float vel) {
		super(name, x1, y1, x2, y2, angle, path, f);
		this.setVelocity(0, vel);
		timer.schedule(task2, 0, 2000);
		timer.schedule(task3, 1000, 2000);
	}
	
	@Override
	public void danyarNau() { 
		Random r = new Random();
		int aux = r.nextInt(50);
		this.delete();
		if(aux == 0 || aux == 10)
			new PowerUpVel("vel",(int) this.x1,(int) this.y1,(int) this.x1 + 20,(int) this.y1 + 20, 0, "Tango Touhou/KxxSTG/full-20.png", f);
		else if(aux == 2 || aux == 20)
			new PowerUpDis("dis",(int) this.x1,(int) this.y1,(int) this.x1 + 20,(int) this.y1 + 20, 0, "Tango Touhou/KxxSTG/power-20.png", f);
		else if(aux == 1)
			new PowerUpTri("triple",(int) this.x1,(int) this.y1,(int) this.x1 + 20,(int) this.y1 + 20, 0, "Tango Touhou/KxxSTG/1up-20-alt.png", f);
		UI.instance.punts.punts();
		f.w.sfxVolume = 90;
		f.w.playSFX("resources/8d82b5_Galaga_Kill_Enemy_Sound_Effect.wav");
	}
	
	public void moureDreta() {
		this.setVelocity(2, this.velocity[1]);
	}
	
	public void moureEsquerra() {
		this.setVelocity(-2, this.velocity[1]);
	}
	
	 TimerTask task2 = new TimerTask() {
    	@Override
    	public void run() {
    		moureDreta();
    	}
    };
	    
    TimerTask task3 = new TimerTask() {
    	@Override
    	public void run() {
    		moureEsquerra();
    	};
    };
    
	@Override
	public void danyarEnemic() {
		// TODO Auto-generated method stub
		
	}
}
