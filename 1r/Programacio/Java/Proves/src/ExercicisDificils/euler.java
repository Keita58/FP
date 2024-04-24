package ExercicisDificils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class euler {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int num = ohowo.nextInt();
			ArrayList<Integer> nums = new ArrayList<>();
			for(int j = 0; j < num; j++)
				nums.add(ohowo.nextInt());
			
			Collections.sort(nums);
			
			for(int j = 0; j < nums.size()/2; j++) {
				System.out.print(nums.get(j)+nums.get(nums.size()-j-1)+" ");
			}
			System.out.println();
		}
	}
}
