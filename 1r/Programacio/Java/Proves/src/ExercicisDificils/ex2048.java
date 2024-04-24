package ExercicisDificils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ex2048 {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int num = ohowo.nextInt();
			ArrayList<Integer> fila = new ArrayList<>();
			ArrayList<Integer> f = new ArrayList<>();
			ArrayList<Integer> f2 = new ArrayList<>();
			
			for(int j = 0; j < num; j++) {
				fila.add(ohowo.nextInt());
			}

			char direccio = ohowo.next().charAt(0);
			if(direccio == 'R') {
				for(int j = 0; j < fila.size(); j++) {
					if(fila.get(j) != 0)
						f2.add(fila.get(j));
				}
				boolean juntar = true;
				for(int j = f2.size()-1; j > 0 ; j--) {
					if(f2.get(j) == f2.get(j-1) && juntar) {
						f2.set(j, f2.get(j)*2);
						f2.remove(j-1);
						juntar = false;
					}
					else
						juntar = true;
				}
				
				while(f2.size() < fila.size())
					f2.add(0, 0);
				
				for(int j = 0; j < f2.size(); j++)
					System.out.print(f2.get(j)+" ");
				
				System.out.println();
			}
			else {
				for(int j = 0; j < fila.size(); j++) {
					if(fila.get(j) != 0)
						f.add(fila.get(j));
				}
				
				boolean juntar = true;
				for(int j = 0; j < f.size()-1;) {
					if(f.get(j) == f.get(j+1) && juntar) {
						f.set(j, f.get(j)*2);
						f.remove(j+1);
						juntar = false;
					}
					else {
						juntar = true;
						j++;
					}
				}
				
				while(f.size() < fila.size())
					f.add(0);
				
				for(int j = 0; j < f.size(); j++)
					System.out.print(f.get(j)+" ");
				System.out.println();
			}
		}
	}
}
