import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ClubNoHomers {
    public static void main(String[] args) {
        Scanner ohowo = new Scanner(System.in);
        int casos = ohowo.nextInt();
        ohowo.nextLine();

        for(int i = 0; i < casos; i++) {
            List<String> noms = new ArrayList<>();

            String nomsASeparar = ohowo.nextLine();
            String[] nomsSeparats = nomsASeparar.split(" ");
            for(int j = 0; j < nomsSeparats.length; j++) {
                noms.add(nomsSeparats[j]);
            }
            String nomVetat = ohowo.nextLine();

            Iterator<String> itNoms = noms.iterator();
            boolean trobat = false;
            while(itNoms.hasNext()) {
                String aux = itNoms.next();
                if(aux.equals(nomVetat) && !trobat)
                    trobat = true;
                else if(aux.equals(nomVetat) && trobat)
                    itNoms.remove();
            }
            System.out.println(noms);
        }
    }
}
