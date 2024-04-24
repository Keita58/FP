import java.util.Scanner;

public class Exercici_3 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		boolean sortir = false;
		int volum = 1;
		
		while(!sortir) {
			int opcio = ohowo.nextInt();
			
			switch(opcio) {
			case 1:
				if(volum < 5)
					volum++;
				break;
			case 2:
				if(volum > 1)
					volum--;
				break;
			case 3:
				for(int x = 1; x <= 5; x++) {
					if(volum == x)
						System.out.println(x+"X");
					else
						System.out.println(x);
				}
				break;
			case 4:
				sortir = true;
				break;
			default: 
				System.out.println("ME DIJISTE QUE LO ENTENDÍAS");
				break;
			}
		}
	}
}
