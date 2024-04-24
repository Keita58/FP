package TestsField;
import java.util.ArrayList;
import java.util.Scanner;

import Core.Field;
import Core.Sprite;
import Core.Window;

public class TestField {
	
	public static void main(String[] args) {
		
		Field f = new Field();
		Window w = new Window(f);
		Scanner sc = new Scanner(System.in);
		
		
		f.background="b84.png";
		ArrayList<Sprite> sprites = new ArrayList<>();
		Sprite s = new Sprite("rock", 50, 50, 150, 150, "resources/rock1.png");
		System.out.println("a");
		sprites.add(s);
		f.draw(sprites);
		sc.nextLine();
		Sprite s2 = new Sprite("oct", 150, 150, 250,250, "resources/octorok.gif");
		System.out.println("b");
		//f.add(s2);
		f.draw(s2);
		while (true) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			s2.x1+=10;
			s2.x2+=10;
			f.draw(s2);
		}
	}

}
