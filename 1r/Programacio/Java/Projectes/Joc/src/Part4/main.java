package Part4;

import java.awt.Color;

import Core.Field;
import Core.Sprite;
import Core.Window;

public class main {
	
	static Field f = new Field();
	static Window w = new Window(f);

	public static void main(String[] args) throws InterruptedException {
		
		w.musicVolume = 100;
		w.playMusic("resources/12 - Scroll Stage Music.wav");
		
		Projectil man = new Projectil("Làser", 10350, -10350, 10400, -10400, 0, "resources/galaga_bullet_by_shipman84_delz2ac.png", f);
		Nau nau = new Nau("Nau", 650, 675, 700, 725, 0, "resources/space-invaders.png", f, man, 5, 5); // El 5 es la velocitat bàsica i el 5 la velocitat de dispar bàsica
		Puntuacio punts = new Puntuacio("Morts", 20, 30, 20, 30, 0, "Puntuació: ", f);
		Vides vidaNau = new Vides("Vida", 20, 50, 20, 50, 0, "Vides: ", f);
		Temps temps = new Temps("Temps", 20, 70, 20, 70, 0, "Temps: ", f);
		
		UI.getInstance(punts, vidaNau, temps);
		
		Spawner enemics = new Spawner(f, 1300, 1); //L'1 es la velocitat bàsica
		
		Sprite fons = new Sprite("fons_nivell", 0, 0, w.getWidth(), w.getHeight(), 0, "resources/pexels-alexandre-p-junior-7736152.jpg", f);
		fons.orderInLayer = -10;
		fons.solid = false;
		Sprite fons2 = new Sprite("fons_nivell", 0, -w.getHeight(), w.getWidth(), 0, 0, "resources/pexels-alexandre-p-junior-7736152.jpg", f);
		fons2.orderInLayer = -10;
		fons2.solid = false;		
		
		int i = 1;
		while(Vides.getVides() > 0) {
			input(nau);
			if(i%7 == 0) {
				fons.y1 += 5;
				fons.y2 += 5;
				if(fons.y1 > 750) {
					fons.y1 = -w.getHeight();
					fons.y2 = 0;
				}
				fons2.y1 += 5;
				fons2.y2 += 5;
				if(fons2.y1 > 750) {
					fons2.y1 = -w.getHeight();
					fons2.y2 = 0;
				}
			}
			f.draw();
			Thread.sleep(30);
			i++;
		}
		w.stopMusic();
		w.playSFX("galaga/explosion.wav");
		
		enemics.stop();
		f.clear();
		f.setBackground(Color.WHITE);
		Fi fi = new Fi("Pantalla final", 0, 0, w.getWidth(), w.getHeight(), 0, "resources/a05n4ksi9dsq6aos99b35jatvd-86efe5c3de2bd88a300e6dc9cbbbc476.png", f);
		Sprite tempsFi = new Sprite("TempsFi", 610, 650, 670, 670, 0, "Temps final: " + temps.getTemps(), f);
		tempsFi.textColor = Color.black.getRGB();
		tempsFi.orderInLayer = 1;
		tempsFi.solid = false;
		tempsFi.text = true;
		Sprite puntsFi = new Sprite("PuntsFi", 610, 670, 670, 690, 0, "Punts finals: " + punts.getPunts(), f);
		puntsFi.textColor = Color.black.getRGB();
		puntsFi.orderInLayer = 1;
		puntsFi.solid = false;
		puntsFi.text = true;
		f.draw();
	}
	
	public static void input(Nau a) {
		
		if(w.getKeysDown().contains(' '))
			a.moviment(Input4.DISPARAR);
		if(w.getPressedKeys().contains('a')) 
			a.moviment(Input4.ESQUERRA);
		if(w.getPressedKeys().contains('d')) 
			a.moviment(Input4.DRETA);
		if(w.getKeysUp().contains('d')) 
			a.moviment(Input4.QUIET);
		if(w.getKeysUp().contains('a')) 
			a.moviment(Input4.QUIET);
	}
}
