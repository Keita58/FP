import java.util.LinkedList;
import java.util.Scanner;

public class Ex2 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		LinkedList<String> paraula = new LinkedList<>();
		int casos = ohowo.nextInt();
		ohowo.nextLine();
		
		for(int i = 0; i < casos; i++) {
			String[] separat = ohowo.nextLine().split("");
			for(int j = 0; j < separat.length; j++) {
				paraula.add(separat[j]);
			}
			String lletra = paraula.removeFirst();
			int j = 1;
			while(!paraula.isEmpty()) {
				if(paraula.getFirst().equals(lletra)) {
					j++;
					paraula.removeFirst();
				}
				else {
					System.out.print(j+lletra);
					lletra = paraula.removeFirst();
					j = 1;
				}
			}
			System.out.print(j+lletra);
		}
		ohowo.close();
	}
}
