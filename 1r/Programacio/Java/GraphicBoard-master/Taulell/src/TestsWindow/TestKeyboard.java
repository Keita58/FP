package TestsWindow;

import java.util.HashSet;
import java.util.Set;

import Core.Field;
import Core.Window;

public class TestKeyboard {
	
	public static void main(String[] args) throws InterruptedException {
		
		Field f = new Field();
		Window w = new Window(f);
		
		while(true) {
			
			Set<Character> keysDown = w.getKeysDown();
			if(!keysDown.isEmpty()) {
				System.out.println("Tecles que s'acaben d'apretar "+keysDown);
			}
			Set<Character> pressedKeys = w.getPressedKeys();
			if(!pressedKeys.isEmpty()) {
				System.out.println("Tecles actualment apretades "+pressedKeys);
			}
			Set<Character> keysUp = w.getKeysUp();
			if(!keysUp.isEmpty()) {
				System.out.println("Tecles que s'acaben d'aixecar "+keysUp);
			}
			
			
			//si, es molt, es per veure l'exemple millor
			Thread.sleep(300);
			
		}
		
		
		
	}

}
