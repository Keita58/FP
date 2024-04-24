
public class main {

	/*
	 * El main d'aquest projecte escriu un caràcter per pantalla, el resultat d'una suma i 
  	 * el resultat d'un càlcul dins un bucle for amb la funció println().
	 */
	public static void main(String[] args) {
		System.out.println("ª");
		System.out.println(1+1);
		int a = 0;
		int b = 0;
		for(int i = 0; i < 10; i++) {
			b += i+1;
			a += b;
		}
		System.out.println(a + " " + b);
	}
}
