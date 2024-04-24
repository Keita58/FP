package ExercicisDificils;
import java.util.Scanner;

public class Set_i_mig {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int cartes = ohowo.nextInt();
		float countA, countB;
		countA = countB = 0;
		boolean plantA, plantB;
		plantA = plantB = false;
		
		for(int i = 0; i < cartes && (!plantA || !plantB); i++) {
			int carta = ohowo.nextInt();
			if(plantA) {
				if(carta == 10 || carta == 11 || carta == 12)
					countB += 0.5;
				else
					countB += carta;
			}
			else if(plantB) {
				if(carta == 10 || carta == 11 || carta == 12)
					countA += 0.5;
				else
					countA += carta;
			}
			else {
				if(i%2 == 0) {
					if(carta == 10 || carta == 11 || carta == 12)
						countA += 0.5;
					else
						countA += carta;
				}
				else {
					if(carta == 10 || carta == 11 || carta == 12)
						countB += 0.5;
					else
						countB += carta;					
				}
			}
			if((countA == 7 || countA == 7.5) && !plantA)
				plantA = true;
			else if((countB == 7 || countB == 7.5) && !plantB)
				plantB = true;
		}
		if(plantA && countA < 8 && countA >= 7 && plantB == false)
			System.out.println("A");
		else if(plantB && countB < 8 && countB >= 7 && plantA == false)
			System.out.println("B");
		else if(countA > countB)
			System.out.println("A");
		else if(countB > countA)
			System.out.println("B");
		else
			System.out.println("NINGU");
	}
}
