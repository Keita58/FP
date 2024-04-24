package Part4;

import java.awt.Color;

import Core.*;

public class Puntuacio extends Sprite {

	private int punt = 0;
	
	public Puntuacio(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f) {
		super(name, x1, y1, x2, y2, angle,path+"0", f);
		this.orderInLayer = 1;
		this.solid = false;
		this.text = true;
		this.textColor = Color.white.getRGB();
		// TODO Auto-generated constructor stub
	}
	
	public void punts() {
		this.punt += 100;
		String[] aux = this.path.split(" ");
		this.path = aux[0]+" "+punt;
	}
	
	public String getPunts() {
		return Integer.toString(this.punt);
	}
}
