import java.util.Scanner;

public class Exercici1 {

	public static void main(String[] args) {
		Scanner ohwow = new Scanner(System.in);
		
		int num1 = ohwow.nextInt();
		int num2 = ohwow.nextInt();
		int num3 = ohwow.nextInt();
		
		if(num1 == num2 || num1 == num3 || num2 == num3)
			System.out.println("SI");
		else if(num1 == num2-1 || num1 == num3-1) 
			System.out.println("SI");
		else if(num2 == num1-1 || num2 == num3-1)
			System.out.println("SI");
		else if(num3 == num1-1 || num3 == num2-1)
			System.out.println("SI");
		else
			System.out.println("NO");
	}

}
