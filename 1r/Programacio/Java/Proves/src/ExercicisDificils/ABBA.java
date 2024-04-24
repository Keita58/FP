package ExercicisDificils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ABBA {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		ohowo.nextLine();
		
		for(int i = 0; i < casos; i++) {
			String nom = ohowo.nextLine();
			String[] noms = nom.split(", ");
			ArrayList<String> nomsFinal = new ArrayList<>(Arrays.asList(noms));
			String sigles = new String();
			
			int[] pos = new int[3];
			int lloc = 0;
			for(int j = 0; j < noms[noms.length-1].length()-1; j++) {
				if(noms[noms.length-1].charAt(j) == 'i' && noms[noms.length-1].charAt(j-1) == ' ' && noms[noms.length-1].charAt(j+1) == ' ') {
					pos[lloc] = j;
					lloc++;
				}
			}
			
			if(lloc > 1) {		
				String finals = nomsFinal.get(nomsFinal.size()-1);
				nomsFinal.remove(nomsFinal.size()-1);
				
				StringBuilder canvi = new StringBuilder(finals);
				canvi.setCharAt(pos[1], ',');
				String ooo = new String(canvi);
				String[] canvis = ooo.split(", ");
			
				for(int j = 0; j < canvis.length; j++) {
					nomsFinal.add(canvis[j]);
				}
			}
			else {
				String finals = nomsFinal.get(nomsFinal.size()-1);
				nomsFinal.remove(nomsFinal.size()-1);
				String[] canvis = finals.split(" i ");
				
				for(int j = 0; j < canvis.length; j++) {
					nomsFinal.add(canvis[j]);
				}
			}
			
			for(int j = 0; j < nomsFinal.size(); j++) {
				char sigla = Character.toUpperCase(nomsFinal.get(j).charAt(0));
				if(sigla == 'À')
					sigla = 'A';
				else if(sigla == 'È' || sigla == 'É')
					sigla = 'E';
				else if(sigla == 'Ò' || sigla == 'Ó')
					sigla = 'O';
				sigles += sigla;
			}
			System.out.println(sigles);
			
		}
	}
}
