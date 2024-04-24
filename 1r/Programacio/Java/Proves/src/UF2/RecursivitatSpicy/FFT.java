package UF2.RecursivitatSpicy;

import java.util.Scanner;

public class FFT {
    
    public static void main(String[] args) {
        Scanner ohowo = new Scanner(System.in);
        int casos = ohowo.nextInt();

        for(int i = 0; i < casos; i++) {
            int f = ohowo.nextInt();
            int c = ohowo.nextInt();
            int fInici = ohowo.nextInt();
            int cInici = ohowo.nextInt();
            int moviments = ohowo.nextInt();
            int posicions = 0;

            posicions = rec(f, c, fInici, cInici, moviments, posicions);
        }
    }

    private static int rec(int f, int c, int fInici, int cInici, int moviments, int posicions) {
        

        return 0;
    }
}
