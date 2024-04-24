package UF1.Contar;
import java.util.Scanner;

public class Contar_minuscules {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		ohowo.nextLine();
		
		for(int i = 0; i < casos; i++) {
			int count = 0;
			String paraula = ohowo.nextLine();
			for(int j = 0; j < paraula.length(); j++) {
				if(Character.isLowerCase(paraula.charAt(j)))
					count++;
			}
			System.out.println(count);
		}
	}
}
