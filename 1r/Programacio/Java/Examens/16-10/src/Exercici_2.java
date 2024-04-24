import java.util.Scanner;

public class Exercici_2 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int countJ = 0;
		int countD = 0;
		boolean jiden = false;
		
		while(true) {
			String persona = ohowo.nextLine();
			
			if(persona.equals("JIDEN"))
				countJ++;
			else
				countD++;
			
			if(countJ == 3) {
				jiden = true;
				break;
			}
			else if(countD == 5) {
				break;
			}	
		}
		if(jiden)
			System.out.println("Ha guanyat JIDEN");
		else 
			System.out.println("Ha guanyat DRUMP");
	}
}
