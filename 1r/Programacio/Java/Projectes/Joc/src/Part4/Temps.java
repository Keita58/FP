package Part4;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import Core.Field;
import Core.Sprite;

public class Temps extends Sprite {
	
	public int s;
	public int m;
	public int h;
	Timer act = new Timer();

	public Temps(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f) {
		super(name, x1, y1, x2, y2, angle, path, f);
		this.s = -1;
		this.m = 0;
		this.h = 0;
		this.orderInLayer = 1;
		this.solid = false;
		this.text = true;
		this.textColor = Color.white.getRGB();
		act.schedule(task1, 0, 1000);
		// TODO Auto-generated constructor stub
	}

	public void temps() {
		this.s++;
		if(this.s >= 60) {
			this.s = 0;
			this.m++;
			if(this.m >= 60) {
				this.m = 0;
				this.h++;
			}
		}
		String[] aux = this.path.split(" ");
		if(this.s < 10 && this.m < 10)
			this.path = aux[0]+" "+"0"+m+":"+"0"+s;
		else if(this.s < 10)
			this.path = aux[0]+" "+m+":"+"0"+s;
		else if(this.m < 10)
			this.path = aux[0]+" "+"0"+m+":"+s;
		else
			this.path = aux[0]+" "+m+":"+s;
	}
	
	public void parar() {
		act.cancel();
	}
	
	public String getTemps() {
		if(this.s < 10 && this.m < 10)
			return "0"+h+":"+"0"+m+":"+"0"+s;
		else if(this.s < 10)
			return "0"+h+":"+m+":"+"0"+s;
		else if(this.m < 10)
			return "0"+h+":"+"0"+m+":"+s;
		else
			return "0"+h+":"+m+":"+s;
	}
	
	TimerTask task1 = new TimerTask() {
        @Override
        public void run()
        {
        	temps();
        }
    };
}
