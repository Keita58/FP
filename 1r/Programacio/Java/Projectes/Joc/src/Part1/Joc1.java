package Part1;
import Core.*;

public class Joc1 {
	
	static Field f = new Field();
	static Window w = new Window(f);

	public static void main(String[] args) throws InterruptedException {
		
		Roca roca = new Roca("Roca", 50, 50, 100, 150, 0, "resources/rock1.png", f);
		
		Roca roca2 = new Roca();
		
		Roca roca3 = new Roca("Roca", 300, 300, 350, 350, 0, "resources/rock1.png", f, 5);
		
		Roca roca4 = new Roca(500, 200, 550, 250, 0, f, 5);
		
		Roca roca5 = new Roca(100, 500, 50, 0, f);
		
		System.out.println(roca.name);
		System.out.println(roca2.name);
		System.out.println(roca3.name);
		System.out.println(roca4.name);
		System.out.println(roca5.name);
		System.out.println("Id de la roca 3: " + roca3.getId());
		System.out.println(Roca.comptador);
		
		boolean sortir = false;
        while (!sortir) {
            f.draw();
            Thread.sleep(30);
        }
	}
}
