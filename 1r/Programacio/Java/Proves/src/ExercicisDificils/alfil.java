package ExercicisDificils;
import java.util.Scanner;

public class alfil {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		ohowo.nextLine();
		
		for(int i = 0; i < casos; i++) {
			String pos = ohowo.nextLine();
			String[] posBe = pos.split("");
			switch(posBe[0]) {
			case "a":
				posBe[0] = "1";
				break;
			case "b":
				posBe[0] = "2";
				break;
			case "c":
				posBe[0] = "3";
				break;
			case "d":
				posBe[0] = "4";
				break;
			case "e":
				posBe[0] = "5";
				break;
			case "f":
				posBe[0] = "6";
				break;
			case "g":
				posBe[0] = "7";
				break;
			case "h":
				posBe[0] = "8";
				break;
			}
			int posicioLletra = Integer.parseInt(posBe[0]);
			int posicioNum = Integer.parseInt(posBe[1]);
			
			int topLeft = Math.min(posicioNum, posicioLletra) - 1;
			 
	        // Count bottom right squares
	        int bottomRight = 8 - Math.max(posicioNum, posicioLletra);
	 
	        // Count top right squares
	        int topRight = Math.min(posicioNum, 9 - posicioLletra) - 1;
	 
	        // Count bottom left squares
	        int bottomLeft = 8 - Math.max(posicioNum, 9 - posicioLletra);
	 
	        // Return total count
	        System.out.println(topLeft + topRight + bottomRight + bottomLeft);
		}
	}
}