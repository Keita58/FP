package Projecte;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NauPesada implements Nau, PropertyChangeListener, Comparable<Nau> {

    private String nom;
    private double punts;
    private int saldo;
    private int capacitatMoviment;

    public NauPesada(double punts, String nom, int saldo) {
        this.nom = nom;
        this.punts = punts;
        this.saldo = saldo;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public double getPunts() {
        return punts;
    }

    @Override
    public int getCapacitatMoviment() {
        throw new UnsupportedOperationException("Unimplemented method 'getCapacitatMoviment'");
    }

    @Override
    public int getSaldoRecursos() {
        return saldo;    
    }

    @Override
    public String toString() {
        return "NauPesada [nom=" + nom + ", punts=" + punts + ", saldo=" + saldo + "]";
    }

    /**
     * Funció que es subscriu a l'observer i on s'aplica l'adapter, és a dir, la lògica dels punts.
     * Mirem si l'objecte, és a dir la nau, té un equipament, i si el té entra al adapter.
     *
     * @param evt ObjecteCapturat que rep de la funció RecolectorPunts
     */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
        PuntsAdapter pA;
        ObjecteCapturat obj = (ObjecteCapturat) evt.getOldValue();

        if(obj.getObjecte() instanceof Enemic) {
            System.out.println("Punts de la nau abans de restar: " + this.punts);
            if(obj.getNau() != null && obj.getNau() instanceof EquipamentDecorator)
                pA = new PuntsRestarAdapterImpl((EquipamentDecorator) obj.getNau(), obj.getObjecte());
            else
                pA = new PuntsRestarAdapterImpl(null, obj.getObjecte());
        }
        else{
            System.out.println("Punts de la nau abans de sumar: " + this.punts);
            if(obj.getNau() != null && obj.getNau() instanceof EquipamentDecorator)
                pA = new PuntsSumarAdapterImpl((EquipamentDecorator) obj.getNau(), obj.getObjecte());
            else
                pA = new PuntsSumarAdapterImpl(null, obj.getObjecte());
        }
        setPunts(this.getPunts() + pA.getPunts());
    }

	private void setPunts(double newValue) {
		this.punts = newValue;
        System.out.println("Punts de la Nau Pesada després del càlcul: "+this.punts);
	}

    /**
     * @return Retorna a si mateixa per a que funcioni correctament el Decorator
     */
    @Override
    public Nau getNau() {
        return this;
    }

    @Override
    public String getDescripcio() {
        return "Nau pesada";
    }

    /**
     * @param o Nau a comparar amb l'actual
     * @return retorna l'ordre de les naus segons el nom de manera ascendent.
     */
    @Override
    public int compareTo(Nau o) {
        return this.getNom().compareTo(o.getNom());
    }
}
