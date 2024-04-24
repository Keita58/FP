package TestsField;
import java.awt.Font;
import java.util.ArrayList;

import Core.Field;
import Core.Sprite;
import Core.Window;

public class TestScroll {

	static Field f = new Field();
	static Window w = new Window(f);

	static Mario m = new Mario("Mario", 50, 50, 100, 100, "resources/rock1.png");
	static Texto t = new Texto("texto", 50, 50, 100, 100, "ESTO NO SE MUEVE POR MAS QUE SCROLLEES");
	static Suelo suelo = new Suelo("Suelo", -500, 300, 800, 320, "resources/b84.png");
	static String[] musicas = {"resources/mario.wav","resources/otra.wav"};
	static int cancionAct = 0;
	static int minscroll = 50;
	static int maxscroll = 300;

	public static void main(String[] args) throws InterruptedException {
		
		//activa la musica
		//w.playMusic("otra.wav");
		//pone fuente y color a la letra
		Font fuente = new Font("Monospaced", Font.BOLD, 22);
		int color = 0x0000FF;
		t.font=fuente;
		t.textColor=color;
		//el texto no escrolleara
		t.unscrollable=true;
		
		while (true) {
			input();
			gravity();
			ArrayList<Sprite> sp = new ArrayList<>();
			sp.add(m);
			sp.add(t);
			sp.add(suelo);
			f.draw(sp);
			Thread.sleep(20);
			System.out.println(m.x1+ " "+minscroll+" "+maxscroll);

		}
	}

	private static void input() {
		// TODO Auto-generated method stub
		if (w.getPressedKeys().contains('a')) {
			m.move('a');
			//si vas demasaido a la izquierda haz scroll a la derecha a la misma velocidad que el movimiento
			if(m.x1<minscroll) {
				//scroll positiu: A la dreta.
				f.scroll(1, 0);
				//has d'ajustar minscroll i maxscroll de la mateixa forma
				minscroll-=1;
				maxscroll-=1;
			}
		} else if (w.getPressedKeys().contains('d')) {
			m.move('d');
			//si vas demasaido a la derecha haz scroll a la izquierda a la misma velocidad que el movimiento
			if(m.x1>maxscroll) {
				//scroll positiu: A l'esquerra
				f.scroll(-1, 0);
				//has d'ajustar minscroll i maxscroll de la mateixa forma
				minscroll+=1;
				maxscroll+=1;
			}
		} 
		
		if (w.getPressedKeys().contains('w')) {

			
			w.playSFX("jump.wav");
			m.jump();
		}
		if (w.getPressedKeys().contains('p')) {

			//si esta en 0 pasa a 1, si en 1, pasa a 2 y por tanto a 0
			cancionAct=(cancionAct+1)%2;
			//para la musica
			//w.stopMusic();  opcional.
			//activa la musica con la siguiente cancion.
			//w.playMusic(musicas[cancionAct]);
			//cambia el path del sprite texto, por tanto cambia el textoq ue se muestra
			t.path="La musica actual es "+musicas[cancionAct];
		}

		

	}

	private static void gravity() {
		// TODO Auto-generated method stub
		
		m.fall(f);
		if(m.y1>500) {
			

		}

	}

}
