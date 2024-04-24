package Part4;

import java.awt.Color;

import Core.Field;
import Core.Sprite;

public class Vides extends Sprite{
	
	private static int vides = 3;
	
	public static int getVides() {
		return vides;
	}

	public Vides(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f) {
		super(name, x1, y1, x2, y2, angle, path+vides, f);
		this.orderInLayer = 1;
		this.solid = false;
		this.text = true;
		this.textColor = Color.white.getRGB();
		// TODO Auto-generated constructor stub
	}
	
	public void perdVida() {
		vides--;
		String[] aux = this.path.split(" ");
		this.path = aux[0]+" "+vides;
	}
	
}
