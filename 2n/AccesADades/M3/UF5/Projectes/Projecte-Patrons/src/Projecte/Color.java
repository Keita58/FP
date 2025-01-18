package Projecte;

public class Color extends NauDecorator implements Comparable<Nau> {

    /**
     * Decorador de la nau on li afegeix un color.
     */

    ColorEnum color;
    Nau nau;

    public Color(Nau nau, ColorEnum color) {
        super(nau);
        this.color = color;
        this.nau = nau;
    }

    /**
     * @param o Nau a comparar amb l'actual
     * @return retorna l'ordre de les naus segons el nom de manera ascendent.
     */
    @Override
    public int compareTo(Nau o) {
        return super.getNau().getNom().compareTo(o.getNom());
    }

    @Override
    public String getNom() {
        return nau.getNom();
    }

    @Override
    public double getPunts() {
        return nau.getPunts();
    }

    @Override
    public int getCapacitatMoviment() {
        return 0;
    }

    @Override
    public int getSaldoRecursos() {
        return nau.getSaldoRecursos();
    }

    /**
     * @return retorna la nau a la qual està equipada.
     */
    @Override
    public Nau getNau() {
        return super.getNau();
    }

    /**
     * @return retorna la descripció de la nau a la qual està equipat.
     */
    @Override
    public String getDescripcio() {
        return super.getDescripcio() + " de color " + this.color;
    }

    @Override
    public String toString() {
        return "Color{" +
                "color=" + color +
                ", nau=" + nau +
                '}';
    }
}
