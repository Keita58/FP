package Projecte;

public abstract class NauDecorator implements Nau{

    /**
     * Decorador de nau del qual depenen els decorators d'Equipament i Galàxia i la classe de Color. Necessari per a que
     * la funcionalitat de Decorator funcioni amb les naus, independentment de l'ordre en el que s'afegeixin.
     */

    private Nau nau;

    public NauDecorator(Nau nau) {
        this.nau = nau;
    }

    /**
     * @return retorna la descripció de la nau a la qual està equipat.
     */
    @Override
    public String getDescripcio() {
        return nau.getDescripcio();
    }

    /**
     * @return retorna la nau a la qual està equipada.
     */
    @Override
    public Nau getNau() {
        return nau.getNau();
    }
}
