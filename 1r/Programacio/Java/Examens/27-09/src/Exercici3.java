import java.util.Scanner;

public class Exercici3 {

	public static void main(String[] args) {
		Scanner ohwow = new Scanner(System.in);
		int volta = ohwow.nextInt();
		
		int voltes = volta/180;
		int pes = voltes*5;
		
		System.out.println(pes+" "+volta%180);
	}

}
