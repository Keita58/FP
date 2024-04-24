//import java.util.Locale;
package ExercicisDificils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class Escales {
	
	public static void main(String[] args) {	
		//Locale.setDefault(Locale.US);
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			ArrayList<Integer> nums = new ArrayList<>();
			for(int j = 0; j < 7; j++) {
				nums.add(ohowo.nextInt());
			}
			Collections.sort(nums);
			
			for(int j = 0; j < nums.size(); j++) {
				for(int k = j+1; k < nums.size(); k++) {
					if(nums.get(j) == nums.get(k))
						nums.remove(k);
				}
			}
			
			if(nums.contains(10) && nums.contains(11) && nums.contains(12) && nums.contains(13) && nums.contains(1))
				System.out.println("ESCALA REIAL");
			else if((nums.get(0)+1 == nums.get(1) && nums.get(1)+1 == nums.get(2) && nums.get(2)+1 == nums.get(3) && nums.get(3)+1 == nums.get(4)) || 
					(nums.get(1)+1 == nums.get(2) && nums.get(2)+1 == nums.get(3) && nums.get(3)+1 == nums.get(4) && nums.get(4)+1 == nums.get(5)))
				System.out.println("ESCALA");
			else
				System.out.println("NO");
		}
	}
}
