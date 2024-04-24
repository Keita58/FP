package TestsField;

import Core.Sprite;

public class MovableChar extends Sprite {

	char direccion='d';
	int cooldown=25; 
	int estat = 0;  //0 suelo, 1 subiendo  2 cayendo
	public MovableChar(String name, int x1, int y1, int x2, int y2, String path) {
		super(name, x1, y1, x2, y2, path);
		// TODO Auto-generated constructor stub
	}

	public void moveIzq() {
		x1--; x2--;
		direccion='a';
	}

	public void moveDer() {
		// TODO Auto-generated method stub
		x1++;x2++;
		direccion='d';
	}
	public void moveArr() {
		y1--;y2--;
		direccion='w';
		
	}public void moveAba() {
		y1++;y2++;
		direccion='s';
		
	}


	
	public void update() {
		if(cooldown<25)cooldown++;
	}
	

}
