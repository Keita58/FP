package Part1;

import Core.Field;
import Core.Sprite;

public class Roca extends Sprite{
	
	private int accionsDisponibles; 
	private int id;
	public static int comptador = 0;
	
	public int getId() {
		return id;
	}
	
	public Roca() {
		
		super("Roca", 0, 0, 50, 50, 0, "resources/rock1.png", Joc1.f);
		super.name = "Roca"+(this.comptador+1);
		this.id = (this.comptador+1);
		this.comptador++;
	}
	
	public Roca(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f) {
		
		super(name, x1, y1, x2, y2, angle, path, f);
		super.name = name+(this.comptador+1);
		this.id = (this.comptador+1);
		this.comptador++;
	}
	
	public Roca(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f, int accions) {
		
		super(name, x1, y1, x2, y2, angle, path, f);
		this.accionsDisponibles = accions;
		super.name = name+(this.comptador+1);
		this.id = (this.comptador+1);
		this.comptador++;
	}
	
	public Roca(int x1, int y1, int x2, int y2, double angle, Field f, int accions) {
		
		super("Roca", x1, y1, x2, y2, angle, "resources/rock1.png",f);
		this.accionsDisponibles = accions;
		super.name = "Roca"+(this.comptador+1);
		this.id = (this.comptador+1);
		this.comptador++;
	}
	
	public Roca(int x1, int y1, int mida, double angle, Field f) {
		
		super("Roca", x1, y1, x1+mida, y1+mida, angle, "resources/rock1.png", f);
		this.accionsDisponibles = 50;
		super.name = "Roca"+(this.comptador+1);
		this.id = (this.comptador+1);
		this.comptador++;
	}
}
