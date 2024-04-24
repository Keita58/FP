package ohowo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class exercici2 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		ArrayList<String> llistaDites = new ArrayList<>(Arrays.asList(ohowo.nextLine().split(" ")));
		ArrayList<String> llistaBusca = new ArrayList<>(Arrays.asList(ohowo.nextLine().split(" ")));
		int[] noms = new int[llistaBusca.size()];
		int major = -1;
		int pos = -1;
		
		for(int i = 0; i < noms.length; i++) {
			noms[i] = 0;
		}
		
		for(int k = 0; k < llistaBusca.size(); k++) {
			noms[k] = Collections.frequency(llistaDites, llistaBusca.get(k));
		}
		
		for(int i = 0; i < noms.length; i++) {
			if(noms[i] > major) {
				major = noms[i];
				pos = i;
			}
		}
		
		System.out.println(llistaBusca.get(pos));
	}
}
