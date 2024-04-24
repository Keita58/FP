package TestsField;
import java.awt.Font;
import java.util.ArrayList;

import Core.Field;
import Core.Sprite;
import Core.Window;

public class TestJump {

	static Field f = new Field();
	static Window w = new Window(f);

	static Mario m = new Mario("Mario", 50, 50, 100, 100, "resources/rock1.png");
	static Texto t = new Texto("texto", 50, 50, 100, 100, "COSAS QUE DIGO");
	static Suelo suelo = new Suelo("Suelo", 50, 300, 300, 320, "resources/b84.png");
	static String[] musicas = {"resources/mario.wav","resources/otra.wav"};
	static int cancionAct = 0;

	public static void main(String[] args) throws InterruptedException {
		
		//activa la musica
		w.playMusic("smb.wav");
		//ha de ser de tipus wav o aiff. Teniu un conversor de mp3 a wav aquí
		//https://audio.online-convert.com/convert-to-wav
		
		//pone fuente y color a la letra
		Font fuente = new Font("Monospaced", Font.BOLD, 22);
		int color = 0x0000FF;
		t.font=fuente;
		t.textColor=color;
		
		while (true) {
			input();
			gravity();
			ArrayList<Sprite> sp = new ArrayList<>();
			sp.add(m);
			sp.add(t);
			sp.add(suelo);
			f.draw(sp);
			Thread.sleep(20);

		}
	}

	private static void input() {
		// TODO Auto-generated method stub
		if (w.getPressedKeys().contains('a')) {
			m.move('a');
		} else if (w.getPressedKeys().contains('d')) {
			m.move('d');
		} 
		
		if (w.getPressedKeys().contains('w')) {

			
			if(m.status==0) {
				w.playSFX("jump.wav");
			}
			m.jump();
			
		}
		if (w.getPressedKeys().contains('p')) {

			//si esta en 0 pasa a 1, si en 1, pasa a 2 y por tanto a 0
			cancionAct=(cancionAct+1)%2;
			//para la musica
			//w.stopMusic();  opcional.
			//activa la musica con la siguiente cancion.
			w.playMusic(musicas[cancionAct]);
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
