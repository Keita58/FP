import java.util.Scanner;

public class Exercici2 {

	public static void main(String[] args) {
		Scanner ohwow = new Scanner(System.in);
		String alumne = ohwow.nextLine();
		
		switch(alumne) {
		case "SERGIO":
		case "ERIC":
		case "BUSSI":
			System.out.println("Estimats alumnes i "+alumne);
			break;
		case "DAVID":
		case "JESUS":
		case "ARNAU":
			System.out.println("Estimats submissos");
			break;
		default:
			System.out.println("Estimats Alumnes");
		}
	}

}
