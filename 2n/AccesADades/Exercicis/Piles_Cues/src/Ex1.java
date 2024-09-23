import java.util.LinkedList;
import java.util.Scanner;

public class Ex1 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int fragment = ohowo.nextInt();
		boolean trobat = false;
		ohowo.nextLine();
		LinkedList<String> html = new LinkedList<>();
		
		for(int i = 0; i < fragment; i++) {
			String linia = ohowo.nextLine();
			html.addLast(linia);
		}
		
		while(!html.isEmpty()) {
			String top = html.getFirst();
			if(top.length() < 8) {
				int j = html.size() - 1;
				for(; j > 0; j--) {
					String text = html.get(j).substring(1, html.get(j).length()-1);
					if(text.equals('/'+top.substring(1, top.length()-1))) {
						html.remove(j);
						html.removeFirst();
						break;
					}
				}
				if(j == 0)
					trobat = true;
				if(trobat) {
					System.out.println("Etiquetes mal tancades");
					break;
				}
			}
			else
				html.removeFirst();
		}
		if(!trobat) 
			System.out.println("Etiquetes ben tancades");
	}
}
