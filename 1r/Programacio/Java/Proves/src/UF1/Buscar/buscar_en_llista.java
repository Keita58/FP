package UF1.Buscar;
import java.util.ArrayList;
import java.util.Scanner;

public class buscar_en_llista {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int gots = ohowo.nextInt();
		ArrayList<Integer> llista = new ArrayList<>();
		llista.add(1);
		for(int i = 1; i < gots; i++) 
			llista.add(0);
		int num1 = ohowo.nextInt();
		while(num1 != -1) {
			int num2 = ohowo.nextInt();
			int aux = llista.get(num1);
			int aux2 = llista.get(num2);
			llista.set(num1, aux2);
			llista.set(num2, aux);
			num1 = ohowo.nextInt();
		}
		System.out.println(llista);
		System.out.println(llista.indexOf(1));
	}
}
