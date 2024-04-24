package Part4;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Core.Field;

public class Spawner {
	
	public int xMaxim;
	public float vel;
	public int spawn;
	int mddl;
	int cntr;
	Field pantalla; 
	Timer timer = new Timer();

	public Spawner(Field f, int x, int vel) {
		this.xMaxim = x;
		this.pantalla = f;
		this.vel = vel;
		this.spawn = 1000;
		this.mddl = 10;
		this.cntr = 0;
		timer.schedule(task1, 0, 100);
		timer.schedule(task2, 30000, 30000);
	}
	
	public void generarEnemic() {
		Random r = new Random();
		switch(r.nextInt(10)) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
			int x = r.nextInt(this.xMaxim - 25);
			new EnemicBasic("EnemicBasic", x, 0, x+40, 40, 0, "resources/Boss-Galaga-Sprite.png", pantalla, vel);
			break;
		case 5:
		case 6:
		case 7:
		case 8:
			int x2 = r.nextInt(this.xMaxim - 25);
			new EnemicAmbArmadura("EnemicArmadura", x2, 0, x2+25, 25, 0, "resources/NicePng_galaga-png_8600323.png", pantalla, vel);
			break;
		case 9:
			int x3 = r.nextInt(this.xMaxim - 25);
			new EnemicAmbDispars("EnemicDispara", x3, 0, x3+25, 25, 0, "resources/5ce2059e8521.png", pantalla, vel);
			break;
		}
	}
	
	public void cosa(){
		cntr++;
		if(cntr%mddl==0) {
			generarEnemic();
		}
	}
	
	public void dificultat() {
		if(this.vel < 25)
			this.vel += 0.25;
		if(this.spawn > 1) {
			this.mddl -= 1;
		}
		System.out.println(this.vel);
	}
	
	//Timer representa el temporitzador. Només en cal un
    //tasca. Cada tasca es individual. si vols 5 events necessitaràs 5 tasques.
    TimerTask task1 = new TimerTask() {
        //la funció run indica tot allò que volguem executar
        @Override
        public void run()
        {
            //en aquest cas volem executar la funció de generarEnemic
        	cosa();
        }
    };
    
    TimerTask task2 = new TimerTask() {
        @Override
        public void run()
        {
        	dificultat();
        }
    };
    
    public void stop() {
    	timer.cancel();
    }
}
