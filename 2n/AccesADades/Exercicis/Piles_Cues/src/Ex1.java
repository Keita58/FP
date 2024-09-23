import java.util.LinkedList;
import java.util.Scanner;

public class Ex1 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int fragment = ohowo.nextInt();
		ohowo.nextLine();
		LinkedList<String> html = new LinkedList<>();
		LinkedList<String> htmlCopia = new LinkedList<>();
		
		for(int i = 0; i < fragment; i++) {
			String linia = ohowo.nextLine();
			html.addLast(linia);
			html.addLast(linia);
		}
		
		while(!html.isEmpty()) {
			String top = html.getFirst();
			
		}
	}
}
