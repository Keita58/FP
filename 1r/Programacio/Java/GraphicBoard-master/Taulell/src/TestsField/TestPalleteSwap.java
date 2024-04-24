package TestsField;
import java.util.ArrayList;
import java.util.Scanner;

import Core.Field;
import Core.Sprite;
import Core.Window;

public class TestPalleteSwap {
	
	public static void main(String[] args) {
		
		Field f = new Field();
		Window w = new Window(f);
		Scanner sc = new Scanner(System.in);
		
		
		ArrayList<Sprite> sprites = new ArrayList<>();
		Sprite s = new Sprite("swap1", 50, 50, 100, 100, 0,"resources/swap.png");
		Sprite s2 = new Sprite("swap2", 150, 50, 200, 100, 0, "resources/swap.png");
		System.out.println("a");
		s2.palleteSwap(0x0000FF, 0x00FFFF);  //blau a cyan
		s2.palleteSwap(0x00FF00, 0xFFFF00);  //verd a groc
		sprites.add(s);
		sprites.add(s2);
		f.draw(sprites);
	}

}
