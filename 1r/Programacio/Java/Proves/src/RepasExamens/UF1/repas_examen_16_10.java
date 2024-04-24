package RepasExamens.UF1;
import java.util.Random;
import java.util.Scanner;

public class repas_examen_16_10 {
	public static void exercici1(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int problemesNecessaris = ohowo.nextInt();
		int vegadesAccedides = ohowo.nextInt();
		int countE, countD;
		countE = countD = 0;
		boolean arribatE, arribatD;
		arribatE = arribatD = false;
		
		for(int i = 0; i < vegadesAccedides; i++) {
			int professorE = ohowo.nextInt();
			//System.out.println(professorE);
			int professorD = ohowo.nextInt();
			//System.out.println(professorD);
			
			countE += professorE;
			countD += professorD;
			if(countE >= problemesNecessaris && countD < problemesNecessaris && !arribatE && !arribatD)
				arribatE = true;
			else if(countD >= problemesNecessaris && countE < problemesNecessaris && !arribatE && !arribatD)
				arribatD = true;
		}
		if(arribatE || countE > countD)
			System.out.println("E");
		else if(arribatD || countE < countD)
			System.out.println("D");
	}
	
	public static void exercici2(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		Random ran = new Random();
		int prob = 0;
		int torns = 0;
		boolean pillat = false;
		boolean seguir = true;
		
		while(seguir && torns < 10) {
			int random = ran.nextInt(1,101);
			if(random >= 10) {
				prob++;
			}
			else
				pillat = true;
			System.out.println(random);
			String entra = ohowo.nextLine();
			if(entra.equals("NO"))
				seguir = false;
			torns++;
		}
		
		if(pillat)
			System.out.println("Problemes: 0 Torns: "+torns);
		else
			System.out.println("Problemes: "+prob+" Torns: "+torns);
	}
	
	public static void exercici3(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		String contrasenya = new String();
		boolean seguir = true;
		
		while(seguir) {
			int num = ohowo.nextInt();
			
			switch(num) {
			case 1:
				contrasenya += 'A';
				break;
			case 2:
				contrasenya += 'B';
				break;
			case 3:
				contrasenya += 'C';
				break;
			case 4:
				if(contrasenya.equals("CACA"))
					seguir = false;
				else
					contrasenya = new String();
				break;
			case 5:
				seguir = false;
				break;
			default:
				System.out.println("Robar està malament");
				contrasenya = new String();
			}
		}
		if(contrasenya != null && contrasenya.equals("CACA"))
			System.out.println("OK");
	}
	
	public static void exercici4(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		String enun1 = ohowo.nextLine();
		String enun2 = ohowo.nextLine();
		int count = 0;
		boolean iguals = false;
		
		if(enun1.length() == enun2.length()) {
			for(int i = 0; i < enun1.length(); i++) {
				if(enun1.charAt(i) == enun2.charAt(i))
					count++;
				if(count > enun1.length()/2)
					iguals = true;
			}
			if(iguals)
				System.out.println("SI");
			else
				System.out.println("NO");
		}
		else
			System.out.println("NO");
	}
	
	public static void main(String[] args) {
		//exercici1(null);
		//exercici2(null);
		//exercici3(null);
		//exercici4(null);
	}
}
