package Part4;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import Core.Field;
import Core.Sprite;

public class PowerUpVel extends PowerUp {
	
	Timer timer = new Timer();
	Sprite punts = null;

	public PowerUpVel(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f) {
		super(name, x1, y1, x2, y2, angle, path, f);
		this.setVelocity(0, 3);
	}

	@Override
	public void onCollisionEnter(Sprite sprite) {
		if(sprite instanceof Nau) {
			this.delete();	
			if(((Nau) sprite).vel < 15) 
				((Nau) sprite).vel += 1;
			punts(sprite, false);
			timer.schedule(task1, 2000);
			UI.instance.punts.punts();
			UI.instance.punts.punts();
			f.w.sfxVolume = 80;
			f.w.playSFX("galaga/Galaga - Demons of Death SFX (5).wav");
		}
	}

	@Override
	public void onCollisionExit(Sprite sprite) {
		// TODO Auto-generated method stub
		
	}
	
	public void punts(Sprite sprite, boolean borrar) {
		if(borrar == false) {
			punts = new Sprite("200",(int) sprite.x1,(int) sprite.y1 - 20,(int) sprite.x1 + 10,(int) sprite.y1 - 10, 0, "200", f);
			punts.textColor = Color.red.getRGB();
			punts.orderInLayer = 1;
			punts.solid = false;
			punts.text = true;
		}
		else {
			punts.delete();
		}
	}

	TimerTask task1 = new TimerTask() {
        //la funció run indica tot allò que volguem executar
        @Override
        public void run()
        {
            //en aquest cas volem executar la funció de generarEnemic
                punts(punts, true);
        }
    };
}
