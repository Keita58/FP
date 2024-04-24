package Part3;

import java.util.ArrayList;
import java.util.Random;

import Core.Field;
import Core.Window;
import Part1.Roca;

public class main {
	
	static Field f = new Field();
	static Window w = new Window(f);

	public static void main(String[] args) throws InterruptedException {
		
		ArrayList<Roca> roques = new ArrayList<>();
		ArrayList<Roca> roques2 = new ArrayList<>();
		ArrayList<Roca> plataforma1 = new ArrayList<>();
		ArrayList<Roca> plataforma2 = new ArrayList<>();
		ArrayList<Roca> plataforma3 = new ArrayList<>();
		ArrayList<Roca> plataforma4 = new ArrayList<>();
		int x1, x2;
		x1 = -105;
		x2 = -70;
		
		for(int i = 0; i < 50; i++) {
			roques.add(new Roca("Roca"+i, x1, 700, x2, 735, 0, "resources/rock2.png", f));
			roques2.add(new Roca("Roca"+i, x1, 734, x2, 769, 0, "resources/rock2.png", f));
			
			x1 += 35;
			x2 += 35;
		}
		x1 = 100;
		x2 = 150;
		
		for(int i = 0; i < 3; i++) {
			plataforma1.add(new Roca("Roca"+i, x1, 600, x2, 610, 0, "resources/rock2.png", f));
			
			x1 += 35;
			x2 += 35;
		}
		
		x1 = 400;
		x2 = 450;
		
		for(int i = 0; i < 7; i++) {
			plataforma2.add(new Roca("Roca"+i, x1, 550, x2, 560, 0, "resources/rock2.png", f));
			
			x1 += 35;
			x2 += 35;
		}
		
		x1 = 50;
		x2 = 100;
		
		for(int i = 0; i < 3; i++) {
			plataforma3.add(new Roca("Roca"+i, x1, 500, x2, 510, 0, "resources/rock2.png", f));
			
			x1 += 35;
			x2 += 35;
		}
		
		x1 = 1000;
		x2 = 1050;
		
		for(int i = 0; i < 13; i++) {
			plataforma4.add(new Roca("Roca"+i, x1, 600, x2, 610, 0, "resources/rock2.png", f));
			
			x1 += 35;
			x2 += 35;
		}
		
		Enemic malo = new Enemic("Maloso", 1200, 650, 1250, 700, 0, "resources/enemigo.gif", f, 10);
		Obstacle vaja = new Obstacle("Pinxo", 500, 500, 525, 525, 0, "resources/spike-up.png", f);
		
		Projectil man = new Projectil("Destral", 10350, 10350, 10400, 10400, 0, "resources/Iron_Axe.png", f);
		
		Personatge a = new Personatge("Link", 600, 650, 650, 700, 0, "resources/Link1.gif", f, man);
		
		f.lockScroll(a, w, true, false);
		
		boolean sortir = false;
		int i = 0;
		while (!sortir) {
        	input(a);
        	if(i%60 == 0)
        		random(malo);
            f.draw();
            Thread.sleep(30);
            i++;
        }
	}
	
	public static void random(Enemic e) {
		
		Random r = new Random();
		
		int aux = r.nextInt(2);
		
		switch(aux) {
		case 0:
			e.moure(Input3.ESQUERRA);
			break;
		case 1:
			e.moure(Input3.DRETA);
		}
	}
	
	public static void input(Personatge a) {
		
		if(w.getKeysDown().contains(' '))
			a.moviment(Input3.DISPARAR);
		if(w.getPressedKeys().contains('a')) 
			a.moviment(Input3.ESQUERRA);
		if(w.getPressedKeys().contains('d')) 
			a.moviment(Input3.DRETA);
		if(w.getKeysUp().contains('d')) 
			a.moviment(Input3.QUIET);
		if(w.getKeysUp().contains('a')) 
			a.moviment(Input3.QUIET);
		if(w.getKeysDown().contains('w'))
			a.moviment(Input3.SALT);
	}
}
