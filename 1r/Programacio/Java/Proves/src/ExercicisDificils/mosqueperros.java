package ExercicisDificils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class mosqueperros {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int tropes = ohowo.nextInt();
			ohowo.nextLine();
			
			String noms = ohowo.nextLine();
			ArrayList<String> nomsTrue = new ArrayList<>(Arrays.asList(noms.split(", ")));
			ArrayList<String> perros = new ArrayList<>();
			
			for(int j = 0; j < nomsTrue.size();) {
				if(!nomsTrue.get(j).contains("AntiMosqueperro") && nomsTrue.get(j).contains("Mosqueperro")) {
					perros.add(nomsTrue.get(j));
					nomsTrue.remove(j);
				}	
				else
					j++;
			}
			
			System.out.println(perros);
			System.out.println(nomsTrue);
		}
	}
}
