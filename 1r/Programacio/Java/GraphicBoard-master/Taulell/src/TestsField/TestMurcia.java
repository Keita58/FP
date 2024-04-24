package TestsField;
import java.util.ArrayList;
import java.util.Random;

import Core.Field;
import Core.Window;

public class TestMurcia {
	
	static ArrayList<Murciano> enemigos = new ArrayList<>();

	
	public static void main(String[] args) throws InterruptedException {
		
		Field f = new Field();
		Window w = new Window(f);
		
		
		int contador = 0;
		
		while(true) {
			if(contador%20==0) {
				generarMurciano();
			}
			for (Murciano m : enemigos) {
				m.movimientoNaranja();
				f.draw(m);
			}
			//f.draw(enemigos);
			Thread.sleep(50);
			contador++;
		}
		
	}
	private static void generarMurciano() {
		// TODO Auto-generated method stub
		Random r = new Random();
		Murciano m = new Murciano("Murcianillo", r.nextInt(700), 0, 69, 69, "resources/m.png");
		enemigos.add(m);
		
	}

}
