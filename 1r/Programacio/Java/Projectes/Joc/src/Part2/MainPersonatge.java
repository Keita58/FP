package Part2;

import Core.Field;
import Core.Window;
import Part1.Roca;

public class MainPersonatge {

	static Field f = new Field();
	static Window w = new Window(f);
	
	public static void main(String[] args) throws InterruptedException {
		
		Roca roca = new Roca("Roca", 0, 0, 1360, 30, 0, "resources/rock1.png", f);
		Roca roca2 = new Roca("Roca", 0, 700, 1360, 730, 0, "resources/rock1.png", f);
		
		Personatge a = new Personatge("Link", 350, 350, 400, 400, 0, "resources/Link1.gif", f);
		
		boolean sortir = false;
		while (!sortir) {
        	input(a);
            f.draw();
            Thread.sleep(30);
        }
	}
	
	public static void input(Personatge a) {
		
		if(w.getKeysDown().contains(' '))
			a.moviment(Input2.SALT);
		if(w.getPressedKeys().contains('a')) 
			a.moviment(Input2.ESQUERRA);
		if(w.getPressedKeys().contains('d')) 
			a.moviment(Input2.DRETA);
	}
}
