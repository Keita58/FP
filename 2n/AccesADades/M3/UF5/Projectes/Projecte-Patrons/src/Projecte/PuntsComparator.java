package Projecte;

import java.util.Comparator;

/**
 * Comparador específic de Nau.
 */
public class PuntsComparator implements Comparator<Nau> {

    /**
     * @param a Primera nau a comparar
     * @param b Segona naou a comparar
     * @return retorna l'ordenació descendent de les naus segons la quantitat de punts.
     */
    public int compare(Nau a, Nau b) {
        return Double.compare(b.getPunts(), a.getPunts());
    }
}
