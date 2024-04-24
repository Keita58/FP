package ExercicisDificils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Prim_i_Grossa {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int importInicial = ohowo.nextInt();
			ArrayList<Integer> peticions = new ArrayList<>();
			ArrayList<Integer> completes = new ArrayList<>();
			
			int num = ohowo.nextInt();
			while(num != 0) {
				peticions.add(num);
				num = ohowo.nextInt();
			}
			Collections.sort(peticions);
			
			if(peticions.contains(importInicial))
				System.out.println(1);
			else {
				int count = 0;
				while(count < 100) {
					if(count < peticions.size() && peticions.get(count) <= importInicial) {
						importInicial -= peticions.get(count);
						completes.add(peticions.get(count));
					}
					else
						break;
					count++;
				}
				if(completes.isEmpty())
					System.out.println("0");
				else
					System.out.println(completes.size());
			}
		}
	}
}
