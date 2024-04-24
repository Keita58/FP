import java.util.Scanner;

public class bug {
	public static void main(String[] args) {
		Scanner ohwow = new Scanner(System.in);
		
		int a = ohwow.nextInt();
		String b = ohwow.nextLine();
		System.out.println(a+" "+b); 
		//No imprimeix el text perquè al fer el intro després d'escriure el número el nextLine interpreta el intro com text
		//Per evitar això, s'ha d'escriure un nextLine entre mig per a que funcioni correctament ->
		//int a = ohwow.nextInt();
		//ohwow.nextLine();
		//String b = ohwow.nextLine();
	}
}