package RepasExamens.UF1;
import java.util.Random;
import java.util.Scanner;

public class repas_examen_27_09 {

	public static void main(String[] args) {
		Scanner ohwow = new Scanner(System.in);

		//Exercici 1
		/*int alcada_arbre = ohwow.nextInt();
		int alcada_1 = ohwow.nextInt();
		int alcada_2 = ohwow.nextInt();
		int alcada_3 = ohwow.nextInt();

		int petit = 500;

		if(alcada_1 >= alcada_arbre && alcada_1 < petit) {
				petit = alcada_1;
		}
		if(alcada_2 >= alcada_arbre && alcada_2 < petit) {
				petit = alcada_2;
		}
		if(alcada_3 >= alcada_arbre && alcada_3 < petit) {
				petit = alcada_3;
		}
		System.out.println(petit);*/




		//Exercici 2
		/*double nota = ohwow.nextDouble();
		ohwow.nextLine();
		String nom = ohwow.nextLine();

		if(nota <= 10 && nota >= 0) {
			switch(nom) {
			case "Alex":
			case "Arbolito":
				nota--;
				break;
			case "Pol":
				nota *= 0;
				break;
			case "Arnau":
			case "Ana":
				nota += 2;
				break;
			case "Els del Gregorio":
				nota -= 2;
				break;
			default:
				nota++;
				break;
			}
			System.out.println(nota);
		}
		else if(nota > 10)
			System.out.println(10);
		else
			System.out.println(0);*/




		//Exercici3
		/*int minuts = ohwow.nextInt();

		if(minuts%1440 >= 1320 && minuts%1440 < 1440) {
			System.out.println("RAID");
		}
		else
			System.out.println("OK");*/




		//Exercici4
		/*int ArmaduraEnemic, HpEnemic;

		Random r = new Random();
		int valorDauAtac = r.nextInt(20)+1;
		int valorDauMal = r.nextInt(10)+1;
		ArmaduraEnemic = r.nextInt(20)+1;
		HpEnemic = r.nextInt(10)+1;

		if(valorDauAtac < ArmaduraEnemic)
			System.out.println("FALLO");
		else if(valorDauAtac >= ArmaduraEnemic) {
			if(valorDauAtac == 20)
				HpEnemic -= valorDauMal*2;
			else
				HpEnemic -= valorDauMal;

			if(HpEnemic <= 0)
				System.out.println("MORT");
			else
				System.out.println("FERIT");
		}
		//System.out.println("Dau mal: "+valorDauMal+" Dau atac: "+valorDauAtac);
		//System.out.println("Armadura: "+ArmaduraEnemic+" Hp: "+HpEnemic);*/
	}

}
