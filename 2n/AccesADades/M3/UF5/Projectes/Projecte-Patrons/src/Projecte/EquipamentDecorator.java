package Projecte;

public class EquipamentDecorator extends NauDecorator implements Comparable<Nau>{

    /**
     * Decorador de la nau on li afegeix un Equipament.
     */
    Equipament equipament;
    Nau nau;

    public EquipamentDecorator(Nau nau, Equipament equipament) {
        super(nau);
        this.equipament = equipament;
        this.nau = nau;
    }

    /**
     * @return retorna la nau a la qual està equipada.
     */
    @Override
    public Nau getNau() {
        return super.getNau();
    }

    public void setNau(Nau nau) {
        this.nau = nau;
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
        return nau.getCapacitatMoviment();
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
        return super.getDescripcio() + posarEquipament();
    }

    public String posarEquipament() {
        return " amb " + equipament;
    }

    public Equipament getEquipament() {
        return equipament;
    }

    /**
     * @param o Nau amb la que comparem l'actual
     * @return Retorna l'ordre de les naus segons el nom de manera ascendent
     */
    @Override
    public int compareTo(Nau o) {
        return super.getNau().getNom().compareTo(o.getNom());
    }

    @Override
    public String toString() {
        return "EquipamentDecorator{" +
                "equipament=" + equipament +
                ", nau=" + nau +
                '}';
    }
}
