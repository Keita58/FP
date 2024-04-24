package Part2;

import Core.Field;
import Part1.Roca;

public class RocaControlable extends Roca{

	private int accionsDisponibles; 
	private int id;
	public static int comptador = 0;

	public RocaControlable() {
		
		super("Roca", 0, 0, 50, 50, 0, "resources/rock1.png", Joc2.f);
		this.x1 = 0;
		this.x2 = 50;
		this.y1 = 0; 
		this.y2 = 50;
		this.accionsDisponibles = 500;
		super.name = "Roca"+(this.comptador+1);
		this.id = (this.comptador+1);
		this.comptador++;
	}
	
	public RocaControlable(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f) {
		
		super(name, x1, y1, x2, y2, angle, path, f);
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.accionsDisponibles = 5;
		super.name = name+(this.comptador+1);
		this.id = (this.comptador+1);
		this.comptador++;
	}
	
	public RocaControlable(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f, int accions) {
		
		super(name, x1, y1, x2, y2, angle, path, f);
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.accionsDisponibles = accions;
		super.name = name+(this.comptador+1);
		this.id = (this.comptador+1);
		this.comptador++;
	}
	
	public RocaControlable(int x1, int y1, int x2, int y2, double angle, Field f, int accions) {
		
		super("Roca", x1, y1, x2, y2, angle, "resources/rock1.png",f);
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.accionsDisponibles = accions;
		super.name = "Roca"+(this.comptador+1);
		this.id = (this.comptador+1);
		this.comptador++;
	}
	
	public RocaControlable(int x1, int y1, int mida, double angle, Field f) {
		
		super("Roca", x1, y1, x1+mida, y1+mida, angle, "resources/rock1.png", f);
		this.x1 = x1;
		this.x2 = x1;
		this.y1 = y1;
		this.y2 = y1;
		this.accionsDisponibles = 50;
		super.name = "Roca"+(this.comptador+1);
		this.id = (this.comptador+1);
		this.comptador++;
	}
	
	public void moviment(Input in) {
		
		if(this.accionsDisponibles > 0) {
			switch(in) {
				case AMUNT: 
					this.y1--;
					this.y2--;
					break;
				case AVALL:
					this.y1++;
					this.y2++;
					break;
				case ESQUERRA:
					this.x1--;
					this.x2--;
					break;
				case DRETA: 
					this.x1++;
					this.x2++;
					break;
				case GRAN:
					break;
				case PETIT:
					break;
				default:
				break;
			}
			this.accionsDisponibles--;
		}
		else
			this.delete();
	}
}
