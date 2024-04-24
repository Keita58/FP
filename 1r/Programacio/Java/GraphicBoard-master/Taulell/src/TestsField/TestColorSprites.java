package TestsField;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

import Core.Field;
import Core.Sprite;
import Core.Window;

public class TestColorSprites {
	
	
	public static void main(String[] args) {
		Field f = new Field();
		Window w = new Window(f);
		Scanner sc = new Scanner(System.in);
		
		Sprite s = new Sprite("rock", 50, 50, 100, 100, 0,"resources/rock1.png");
		
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		
		Color col = new Color(0, 0, 255, 255);
		Sprite quadratBlau = new Sprite("quadratBlau", 100, 100, 200, 200, 0, col);
		
		Color col2 = new Color(255, 0, 0, 50);
		Sprite vermelltrans = new Sprite("vermelltrans", 300, 100, 400, 200, 0, col2);
		
		sprites.add(quadratBlau);
		sprites.add(vermelltrans);
		f.draw(sprites);
		
		
		
		
	}
	


}
