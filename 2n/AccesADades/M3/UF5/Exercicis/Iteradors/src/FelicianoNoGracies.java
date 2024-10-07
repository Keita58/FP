import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class FelicianoNoGracies {
    public static void main(String[] args) {
        Scanner ohowo = new Scanner(System.in);
        int alumnes = ohowo.nextInt();
        ohowo.nextLine();
        List<String> noms = new ArrayList<>();

        for(int i = 0; i < alumnes; i++) {
            noms.add(ohowo.nextLine());
        }

        ListIterator<String> itNoms = noms.listIterator();
        boolean F, S, seguir;
        F = S = false;
        seguir = false;

        while(itNoms.hasNext()) {
            String lletra = itNoms.next();
            if(lletra.equals("F") && S)
                S = false;
            else if(lletra.equals("F")) 
                F = true;
            else if(lletra.equals("S") && F)
                S = true;
            else if(lletra.equals("C") && F && S) {
                itNoms.previous();
                itNoms.remove();
                itNoms.previous();
                itNoms.remove();
                itNoms.previous();
                itNoms.remove();
                itNoms = noms.listIterator();
            }
            else
                F = S = false;
        }
        System.out.println(noms.size());
    }
}
