import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class exercicis {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int k = 0; k < casos; k++) {
			int numPilots = ohowo.nextInt();
			ohowo.nextLine();
			String nomPilots = ohowo.nextLine();
			ArrayList<String> entrada = new ArrayList<>(Arrays.asList(nomPilots.split(" "))); 
			ArrayList<String> sortida = new ArrayList<>(Arrays.asList(nomPilots.split(" ")));
			
			for(int i = 0; i < numPilots; i++) {
				int mov = ohowo.nextInt();
				int pos = sortida.indexOf(entrada.get(i));
				sortida.remove(entrada.get(i));
				sortida.add(mov+pos, entrada.get(i));
			}
			System.out.println(sortida);
		}
	}
}