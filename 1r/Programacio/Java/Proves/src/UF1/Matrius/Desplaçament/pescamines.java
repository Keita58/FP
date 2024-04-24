package UF1.Matrius.Desplaçament;

import java.util.Scanner;

public class pescamines {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		int casos = ohowo.nextInt();
		
		for(int i = 0; i < casos; i++) {
			int l = ohowo.nextInt();
			int f = ohowo.nextInt();
			int c = ohowo.nextInt();
			int[][] matriu = new int[f][c];
			
			for(int j = 0; j < f; j++) {
				for(int k = 0; k < c; k++) {
					matriu[j][k] = ohowo.nextInt();
				}
			}
			int posx = ohowo.nextInt();
			int posy = ohowo.nextInt();
			int mines = 0;
			if(matriu[posx][posy] != 1) {
				for(int j = 0; j < 8; j++) {
					switch(j) {
					case 0:
						if(posx > 0 && posy > 0 && matriu[posx-1][posy-1] == 1)
							mines++;
						break;
					case 1:
						if(posx > 0 && matriu[posx-1][posy] == 1)
							mines++;
						break;
					case 2:
						if(posx > 0 && matriu[posx-1][posy+1] == 1)
							mines++;
						break;
					case 3:
						if(posy > 0 && matriu[posx][posy-1] == 1)
							mines++;
						break;
					case 4:
						if(matriu[posx][posy+1] == 1)
							mines++;
						break;
					case 5:
						if(posy > 0 && matriu[posx+1][posy-1] == 1)
							mines++;
						break;
					case 6:
						if(matriu[posx+1][posy] == 1)
							mines++;
						break;
					case 7:
						if(matriu[posx+1][posy+1] == 1)
							mines++;
						break;
					}
				}
				System.out.println(mines);
			}
			else
				System.out.println("BOOM");
		}
	}
}
