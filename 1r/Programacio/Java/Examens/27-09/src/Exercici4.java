import java.util.Random;
import java.util.Scanner;

public class Exercici4 {

	public static void main(String[] args) {
		Scanner ohwow = new Scanner(System.in);
		Random r = new Random();
		
		int corbates = r.nextInt(0,11);
		boolean cinturo = false;
		
		
		if(corbates >= 1) {
			if(corbates <= 3)
				System.out.println("Et serveixen cafè amb un 5% de descompte.");
			else {
				if(corbates >= 4) {
					if(corbates <= 9) {
						cinturo = ohwow.nextBoolean();
						if(cinturo)
							System.out.println("Et serveixen cafè amb un 15% de descompte.");
						else
							System.out.println("Et serveixen cafè amb un 10% de descompte.");
					}
					else
						System.out.println("Et serveixen cafè gratis.");
				}
			}
		}
		else
			System.out.println("Fot el camp d'aquí sense-corbater.");
	}

}
