package Extres.Arrays.Hard;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class trol {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		ohowo.nextLine();
		
		for(int i = 0; i < casos; i++) {
			String li = ohowo.next();
			String ri = ohowo.next();
			long resta = Long.parseLong(ri) - Long.parseLong(li);
			int numIni = 0;
			long numFi = 0;
			
			for(int j = 0; j < li.length(); j++) {
				numIni += li.charAt(j) - 48;
				if(numIni > 10) {
					int aux = numIni - 10;
					numIni = aux+1;
				}
			}
			
			long vegades = resta;
			vegades /= 9;
			vegades *= 45;
			
			for(int j = 0; j < ri.length(); j++) {
				numFi += ri.charAt(j) - 48;
				if(numFi > 10) {
					long aux = numFi - 10;
					numFi = aux+1;
				}
			}
			
			if(resta > 9) {
				for(int j = numIni; j < 10; j++) {
					vegades += j;
				}
				for(long j = numFi; j > 0; j--) {
					vegades += j;
				}
			}
			else {
				Integer[] llista = {1,2,3,4,5,6,7,8,9};
				Collections.rotate(Arrays.asList(llista), 10-numIni);
				for(int j = 0; ; j++) {
					vegades += llista[j];
					if(llista[j] == numFi)
						break;
				}
			}

			System.out.println(vegades);
		}
	}
}
