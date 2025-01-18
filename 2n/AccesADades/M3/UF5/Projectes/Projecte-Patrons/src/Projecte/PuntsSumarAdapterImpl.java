package Projecte;

public class PuntsSumarAdapterImpl implements PuntsAdapter{
    
    private Equipament eq;
    private Object obj;
    
    public PuntsSumarAdapterImpl(EquipamentDecorator eq, Object o){
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
     * Funció que calcula la suma de punts a la nau depenent de l'objecte que hagi agafat la nau.
     * Si aquesta nau té un equipament, els punts a sumar es multipliquen pel factor d'aquest Equipament.
     * @return El càlcul dels punts, tant si és necessari multiplicar el factor com no
     */
    private double multFactor(){
        if(eq != null) {
            if(obj instanceof Bonus) {
                System.out.println("Punts a sumar: " + ((Bonus) obj).puntsASumar());
                System.out.println("Factor a dividir: " + eq.getFactor());
                return ((Bonus) obj).puntsASumar() / eq.getFactor();
            }
            else {
                System.out.println("Punts a sumar: " + ((Recurs) obj).puntsASumar());
                System.out.println("Factor a dividir: " + eq.getFactor());
                return ((Recurs) obj).puntsASumar() / eq.getFactor();
            }
        }
        else {
            if(obj instanceof Bonus) {
                System.out.println("Punts a sumar: " + ((Bonus) obj).puntsASumar());
                System.out.println("Sense factor a aplicar");
                return ((Bonus) obj).puntsASumar();
            }
            else {
                System.out.println("Punts a sumar: " + ((Recurs) obj).puntsASumar());
                System.out.println("Sense factor a aplicar");
                return ((Recurs) obj).puntsASumar();
            }
        }
    }
}
