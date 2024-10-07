import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class NoCountryForOldMen {
    public static void main(String[] args) {
        Scanner ohowo = new Scanner(System.in);
        int casos = ohowo.nextInt();

        for(int i = 0; i < casos; i++) {
            List<Integer> edats = new ArrayList<>();
            int edat = ohowo.nextInt();
            
            while(edat != 0) {
                edats.add(edat);
                edat = ohowo.nextInt();
            }

            int vell = ohowo.nextInt();
            Iterator<Integer> itEdats = edats.iterator();

            while(itEdats.hasNext()) {
                if(itEdats.next() >= vell)
                    itEdats.remove();
            }
            System.out.println(edats);
        }
    }
}
