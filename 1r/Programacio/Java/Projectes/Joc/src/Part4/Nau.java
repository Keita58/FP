package Part4;

import java.util.Timer;
import java.util.TimerTask;

import Core.Field;
import Core.PhysicBody;
import Core.Sprite;

public class Nau extends PhysicBody implements Disparable{
	
	private Projectil pium;
	public float vel;
	public int dis;
	public float aux;
	float mddl;
	int cntr;
	public boolean triple;
	public boolean doble;
	public boolean active;
	Timer bales = new Timer();

	public Nau(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f, Projectil a, float vel, int dis) {
		super(name, x1, y1, x2, y2, angle, path, f);
		this.pium = a;
		this.vel = vel;
		this.dis = dis;
		this.triple = false;
		this.doble = false;
		this.active = false;
		this.mddl = 10;
		this.cntr = 0;
		bales.schedule(task1, 0, 50);
	}

	@Override
	public void onCollisionEnter(Sprite sprite) {
		if(sprite instanceof Enemic) {
			if(Vides.getVides() == 1) {
				this.delete();
			}
			UI.instance.vida.perdVida();
			this.canviaDisseny();
			f.w.sfxVolume = 80;
			f.w.playSFX("galaga/fighter_destroyed.wav");
		}
	}

	@Override
	public void onCollisionExit(Sprite sprite) {
		// TODO Auto-generated method stub
		
	}
	
	public void disparar() {
		if(!this.delete) {
			if(this.triple) {
				if(!active) {
					bales.schedule(new TimerTask() {@Override public void run() {triple = false; active = false; mddl = aux;}}, 10000);
					this.active = true;
					this.aux = this.mddl;
					this.mddl = 3;
				}
				Projectil newPium = new Projectil(this.pium, (int) this.x1, (int) this.y1, (int) this.x2, (int) this.y2);
				Projectil newPium2 = new Projectil(this.pium, (int) this.x1 - 20, (int) this.y1, (int) this.x2 - 20, (int) this.y2);
				Projectil newPium3 = new Projectil(this.pium, (int) this.x1 + 20, (int) this.y1, (int) this.x2 + 20, (int) this.y2);
				newPium.setVelocity(0, -dis);
				newPium2.setVelocity(0, -dis);
				newPium3.setVelocity(0, -dis);
			}
			else if(this.doble) {
				Projectil newPium2 = new Projectil(this.pium, (int) this.x1 - 20, (int) this.y1, (int) this.x2 - 20, (int) this.y2);
				Projectil newPium3 = new Projectil(this.pium, (int) this.x1 + 20, (int) this.y1, (int) this.x2 + 20, (int) this.y2);
				newPium2.setVelocity(0, -dis);
				newPium3.setVelocity(0, -dis);
			}
			else {
				Projectil newPium = new Projectil(this.pium, (int) this.x1, (int) this.y1, (int) this.x2, (int) this.y2);
				newPium.setVelocity(0, -dis);
			}
			f.w.sfxVolume = 80;
			f.w.playSFX("resources/8d82b5_Galaga_Firing_Sound_Effect.wav");
		}
	}

	public void moviment(Input4 in) {
		switch(in) {
			case ESQUERRA:
				if(this.x1 > 10)
					this.setVelocity(-vel, 0);
				else
					this.velocity[0] = 0;
				break;
			case DRETA:
				if(this.x2 < 1333)
					this.setVelocity(vel, 0);
				else
					this.velocity[0] = 0;
				break;
			case QUIET:
				this.setVelocity(0, 0);
				break;
//			case DISPARAR:
//				this.disparar();
//				break;
			default:
				break;
		}
	}
	
	 TimerTask task1 = new TimerTask() {
        //la funció run indica tot allò que volguem executar
        @Override
        public void run()
        {
        	velocitatDispar();
        }
    };
    
    TimerTask task3 = new TimerTask() {
        //la funció run indica tot allò que volguem executar
        @Override
        public void run()
        {
        	triple = false;  
        	active = false;
        	mddl = aux;
        }
    };
    
	public void velocitatDispar(){
		cntr++;
		if(cntr%mddl==0) {
			disparar();
		}
	}

	@Override
	public void danyarEnemic() {
		// TODO Auto-generated method stub
		if(Vides.getVides() == 1)
			this.delete();
		UI.instance.vida.perdVida();
		this.canviaDisseny();
		f.w.sfxVolume = 80;
		f.w.playSFX("galaga/fighter_destroyed.wav");
	}

	@Override
	public void danyarNau() {
		// TODO Auto-generated method stub
		
	}
	
	public void canviaDisseny() {
		this.changeImage("resources/space-invaders-inverted.png");
		this.changeImage("resources/space-invaders.png");
		this.changeImage("resources/space-invaders-inverted.png");
		this.changeImage("resources/space-invaders.png");
		this.changeImage("resources/space-invaders-inverted.png");
		this.changeImage("resources/space-invaders.png");
	}
}
