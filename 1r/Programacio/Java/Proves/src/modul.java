import java.util.Scanner;

public class modul {

	public static void main(String[] args) {
		Scanner ohwow = new Scanner(System.in);
		
		int num = ohwow.nextInt();
		int modul = num%2;
		System.out.println(modul);
	}

}
