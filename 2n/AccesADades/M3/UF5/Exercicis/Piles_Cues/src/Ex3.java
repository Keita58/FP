import java.util.LinkedList;
import java.util.Scanner;

public class Ex3 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		boolean afegir = true;
		LinkedList<String> treballs = new LinkedList<>();
		LinkedList<String> resultat = new LinkedList<>();
		
		for(int i = 0; i < casos; i++) {
			int numCasos = ohowo.nextInt();
			ohowo.nextLine();
			for(int j = 0; j < numCasos; j++) 
				treballs.add(ohowo.nextLine());
			
			while(!treballs.isEmpty()) {
				String aux = treballs.removeFirst();
				String[] separat = aux.split("-");
				if(separat.length == 1) {
					resultat.add(separat[0]);
				}
				else {
					String[] separat2 = separat[1].split(",");
					for(int j = 0; j < separat2.length; j++) {
						if(!resultat.contains(separat2[j])) {
							afegir = false;
							break;
						}
					}
				}
				if(!afegir) {
					treballs.addLast(aux);
					afegir = true;
				}
				else if(separat.length > 1)
					resultat.add(separat[0]);
			}
			while(!resultat.isEmpty()) {
				System.out.print(resultat.removeFirst() + " ");
			}
		}
		ohowo.close();
	}
}
