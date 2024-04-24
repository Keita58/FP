package TestsField;

import java.awt.Font;
import java.util.ArrayList;

import Core.Field;
import Core.Sprite;
import Core.Window;

public class TestMouse {
	
	static Field f = new Field();
	static Window w = new Window(f);

	static Texto t = new Texto("texto", 50, 50, 75, 75, "sadadasdadasd");
	static Texto t2 = new Texto("texto", 75, 75, 100, 100, "sadadasdadasd");
	static Texto t3 = new Texto("texto", 100, 100, 125, 125, "sadadasdadasd");
	static Texto t4 = new Texto("texto", 125, 125, 150, 150, "sadadasdadasd");

	static Rock b = new Rock("Rock", 150, 150, 250, 250, 0, "resources/rock2.png");
	static Rock b2 = new Rock("RotatedRock", 350, 150, 450, 250, 45, "resources/rock2.png");
	
	public static void main(String[] args) throws InterruptedException {
		Font fuente = new Font("Monospaced", Font.BOLD, 18);
		int color = 0x0000FF;
		t.font=fuente;
		t.textColor=color;
		w.changeSize(1000, 300);
		
		
		while(true){

			ArrayList<Sprite> sprites = new ArrayList<>();
			
			int x = f.getMouseX();
			int y = f.getMouseY();
			int rx = f.getRightMouseX();
			int ry = f.getRightMouseY();
			int ox = f.getMouseOverX();
			int oy = f.getMouseOverY();
			
			String str = "l'�ltim pixel premut amb el bot� esquerre �s "+x+", "+y+"\n, i amb el dret "+rx+", "+ry;
			t.path=str;
			sprites.add(t);
			sprites.add(b);
			sprites.add(b2);
			
			String str2="He clickat a sobre dels Sprites: ";
			for (Sprite s : sprites) {
				if(s.collidesWithPoint(x, y)||s.collidesWithPoint(rx, ry)) {
					str2+=s.name+" ";
				}
			}
			t2.path=str2;
			sprites.add(t2);
			
			
			
			String str3 = "El ratoli esta fent mouseOver sobre el pixel"+ox+", "+oy+"\n";
			t3.path=str3;
			sprites.add(t3);
			
			String str4="El ratoli esta sobre dels sprites a sobre dels Sprites: ";
			for (Sprite s : sprites) {
				if(s.collidesWithPoint(ox, oy)) {
					str4+=s.name+" ";
				}
			}
			t4.path=str4;
			sprites.add(t4);
			
			f.draw(sprites);
			
			
			
			
			Thread.sleep(50);
			
		}
	}

}
