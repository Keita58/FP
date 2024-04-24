package TestsField;
import java.util.ArrayList;
import java.util.Scanner;

import Core.Field;
import Core.Sprite;
import Core.Window;

public class TestRotate {
	
	public static void main(String[] args) {
		
		Field f = new Field();
		Window w = new Window(f);
		Scanner sc = new Scanner(System.in);
		
		

		f.background="resources/rainb.jpg";
		ArrayList<Sprite> sprites = new ArrayList<>();
		///un angle de 0 (o no posar angle) equival a no rotaci�
		Sprite s = new Sprite("rock", 50, 50, 100, 100, 0,"resources/rock1.png");
		///implementar un sprite amb rotacio, el 5� parametre es l'angle  de rotacio (double)
		//gira sobre el propi centre.
		Sprite s2 = new Sprite("otherrock", 150, 50, 200, 100, 45, "resources/rock2.png");
		System.out.println("a");
		sprites.add(s);
		sprites.add(s2);
		f.draw(sprites);
		sc.nextLine();
		Sprite s3 = new Sprite("oct", 150, 150, 250,250, 45,"resources/octorok.gif");
		System.out.println("b");
		//f.add(s2);
		f.draw(s3);
		while (true) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//el moviment i la rotacio son indepents, no s'afecten
			s3.x1+=10;
			s3.x2+=10;
			f.draw(s3);
		}
	}

}
