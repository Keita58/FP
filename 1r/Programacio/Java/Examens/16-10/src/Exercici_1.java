import java.util.Scanner;

public class Exercici_1 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int anys = ohowo.nextInt();
		
		int vicA = 0;
		int empA = 0;
		int derA = 0;
		int vicB = 0;
		int empB = 0;
		int derB = 0;
		int vicC = 0;
		int empC = 0;
		int derC = 0;
		
		int puntA, puntB, puntC;
		puntA = puntB = puntC = 0;
		
		for(int i = 0; i < anys; i++) {
			vicA += ohowo.nextInt();
			empA += ohowo.nextInt();
			derA += ohowo.nextInt();
			vicB += ohowo.nextInt();
			empB += ohowo.nextInt();
			derB += ohowo.nextInt();
			vicC += ohowo.nextInt();
			empC += ohowo.nextInt();
			derC += ohowo.nextInt();
		}
		if(vicA + vicB + vicC != derA + derB + derC)
			System.out.println("NO");
		else {
			puntA = vicA*3 + empA;
			puntB = vicB*3 + empB;
			puntC = vicC*3 + empC;
			
			if(puntA > puntB && puntA > puntC)
				System.out.println("A");
			else if(puntB > puntA && puntB > puntC)
				System.out.println("B");
			else
				System.out.println("C");
		}
	}
}
