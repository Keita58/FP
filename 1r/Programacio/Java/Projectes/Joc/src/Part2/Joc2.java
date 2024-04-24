package Part2;

import Core.Field;
import Core.Window;

public class Joc2 {

	static Field f = new Field();
	static Window w = new Window(f);
	
	public static void main(String[] args) throws InterruptedException {
		
		//Roca roca = new Roca("Roca", 50, 50, 100, 150, 0, "resources/rock1.png", f);
		
		RocaControlable roca2 = new RocaControlable();		
		
		boolean sortir = false;
        while (!sortir) {
        	imput(roca2);
            f.draw();
            Thread.sleep(30);
        }
	}
	
	public static void imput(RocaControlable roca) {
		
		if( w.getPressedKeys().contains('w'))
			roca.moviment(Input.AMUNT);
		if( w.getPressedKeys().contains('a')) 
			roca.moviment(Input.ESQUERRA);
		if( w.getPressedKeys().contains('s'))
			roca.moviment(Input.AVALL);
		if( w.getPressedKeys().contains('d')) 
			roca.moviment(Input.DRETA);
	}
}
