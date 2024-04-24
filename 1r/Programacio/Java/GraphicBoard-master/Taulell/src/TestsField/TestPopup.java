package TestsField;

import Core.Field;
import Core.Window;

public class TestPopup {
	
	
	public static void main(String[] args) throws InterruptedException {
		Field f = new Field();
		Window w = new Window(f);
		
		w.showPopup("cosas");
		
		while(!w.getPressedKeys().contains(' ')) {
			Thread.sleep(100);
		};
		
		String nom = w.showInputPopup("dime tu nombre");
		
		System.out.println("tu nombre es "+nom);
		
	}

}
