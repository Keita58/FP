package Part4;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Core.Field;

public class EnemicAmbDispars extends Enemic implements Disparable{
	
	Timer timer = new Timer();
	private ProjectilEnemic pium;

	public EnemicAmbDispars(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f, float vel) {
		super(name, x1, y1, x2, y2, angle, path, f);
		this.setVelocity(0, vel);
		this.pium = new ProjectilEnemic("Làser", 10350, -10350, 10400, -10400, 90, "resources/pixil-frame-0.png", f);
		timer.schedule(task1, 0, 2000);
	}
	
	public void disparar() {
		if(!this.delete) {
			this.pium = new ProjectilEnemic(this.pium, (int) this.x1, (int) this.y1 + 30, (int) this.x2, (int) this.y2 + 30);
			this.pium.setVelocity(0, 7);
		}
	}

	TimerTask task1 = new TimerTask() {
    	@Override
    	public void run() {
    		disparar();
    	}
    };
	
	@Override
	public void danyarNau() {
		// TODO Auto-generated method stub
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
		UI.instance.punts.punts();
		f.w.sfxVolume = 80;
		f.w.playSFX("galaga/Galaga - Demons of Death SFX (5).wav");
	}

	@Override
	public void danyarEnemic() {
		// TODO Auto-generated method stub
		
	}
}
