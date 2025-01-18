package Projecte;

public class GalaxiaDecorator extends NauDecorator implements Comparable<Nau>{

    /**
     * Decorador de la nau on li afegeix una galàxia.
     */

    Galaxia galaxia;
    Nau nau;
    
    public GalaxiaDecorator(Nau nau, Galaxia galaxia) {
        super(nau);
        this.nau = nau;
        this.galaxia = galaxia;
    }

    /**
     * @param o the object to be compared.
     * @return retorna l'ordre de les naus segons el nom de manera ascendent.
     */
    @Override
    public int compareTo(Nau o) {
        return this.nau.getNom().compareTo(o.getNom());
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
     * @return retorna la descripció de la nau a la qual està equipat.
     */
    @Override
    public String getDescripcio() {
        return super.getDescripcio() + getGalaxia();
    }

    public String getGalaxia() {
        return " amb " + galaxia;
    }

    /**
     * @return retorna la nau a la qual està equipada.
     */
    @Override
    public Nau getNau() {
        return super.getNau();
    }

    @Override
    public String toString() {
        return "GalaxiaDecorator{" +
                "galaxia=" + galaxia +
                ", nau=" + nau +
                '}';
    }
}
