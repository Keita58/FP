package Projecte;

public class PuntsRestarAdapterImpl implements PuntsAdapter {

    private Equipament eq;
    private Object obj;
    
    public PuntsRestarAdapterImpl(EquipamentDecorator eq, Object o){
        if(eq != null)
            this.eq = eq.getEquipament();
        else
            this.eq = null;
        this.obj = o;
    }
    
    @Override
    public double getPunts() {
        return multFactor();
    }

    /**
     * Funció que mira si hi ha un equipament i si n'hi ha nultiplica el seu facot pels punts a restar de l'enemic (l'objecte).
     * @return retorna la multiplicació entre el factor de l'equipament i els punts a restar de l'enemic.
     * Si no hi ha equipament retorna només els punts a restar de l'enemic.
     */
    private double multFactor(){
        if(eq != null) {
            System.out.println("Punts a restar: " + ((Enemic) obj).getPuntsARestar());
            System.out.println("Factor a multiplicar: " + eq.getFactor());
            return ((Enemic) obj).getPuntsARestar() * eq.getFactor();
        }
        else {
            System.out.println("Punts a restar: " + ((Enemic) obj).getPuntsARestar());
            System.out.println("Sense factor a aplicar");
            return ((Enemic) obj).getPuntsARestar();
        }
    }
}
