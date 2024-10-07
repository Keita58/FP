package Ex4;

import java.util.ArrayList;
import java.util.List;

public class main {
  public static void main(String[] args) {
    List<String> noms = List.of("Mario", "Luigi", "Peach", "Daisy");
    System.out.println(intercanvi(noms));

    List<Double> numeros = List.of(3.5, -5.0, 34.8, 2.8);
    System.out.println(intercanvi(numeros));
  }

  public static <E> List<E> intercanvi(List<E> llista) {
    List<E> aux = new ArrayList<>();

    for(int i = llista.size() - 1; i >= 0; i--) {
      aux.add(llista.get(i));
    }

    return aux;
  }
}
