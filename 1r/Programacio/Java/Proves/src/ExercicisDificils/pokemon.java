package ExercicisDificils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class pokemon {
    
    public static void main(String[] args) {
        
        Scanner ohowo = new Scanner(System.in);
        int casos = ohowo.nextInt();

        for(int i = 0; i < casos; i++) {
            int nCartes = ohowo.nextInt();
            ohowo.nextLine(); //bug
            ArrayList<String> cartes = new ArrayList<>();
            LinkedHashMap<String, Integer> repetides = new LinkedHashMap<>();

            for(int j = 0; j < nCartes; j++) {
                String carta = ohowo.nextLine();
                if(repetides.containsKey(carta))
                    repetides.put(carta, repetides.get(carta)+1);
                else {
                    repetides.put(carta, 1);
                    cartes.add(carta);
                }
            }

            System.out.print("[");
            for(int j = 0; j < cartes.size() - 1; j++) {
                System.out.print(cartes.get(j) + ", ");
            }
            System.out.println(cartes.get(cartes.size() - 1) + "]");

        	Iterator<Map.Entry<String,Integer>> iter = repetides.entrySet().iterator();
        	Collection<Map.Entry<String,Integer>> cache = new ArrayList<>();
            while(iter.hasNext()) {
            	cache.add(iter.next());
                ArrayList<String> a = new ArrayList<>();
                Map.Entry<String,Integer> entry = iter.next();
            	Iterator<Map.Entry<String,Integer>> iter2 = cache.iterator();

            	while(iter2.hasNext()) {
                    Map.Entry<String,Integer> entry2 = iter2.next();
                	if(entry2.getKey() == entry.getKey())
                		continue;
                    if(entry2.getValue() == entry.getValue() && entry.getValue() > 1) {
                    	if(!a.contains(entry.getKey()))
                    		a.add(entry.getKey());
                        a.add(entry2.getKey());
                        iter2.remove();
                    }
                }
                
                if(!a.isEmpty()) {
                    System.out.print("Les cartes [");
                    for (int k = 0; k < a.size() - 1; k++) {
                        System.out.print(a.get(k) + ", ");
                    }
                    System.out.println(a.get(a.size() - 1) + "] s'han repetit " + entry.getValue() + " vegades");
                }
                else if(entry.getValue() > 1){
                	System.out.println(entry.getKey() + " s'ha repetit " + entry.getValue() + " vegades");
                }
                else {
                    System.out.println("No s'ha repetit cap carta de Pokemon");
                }
            }   
        }
    }
}
