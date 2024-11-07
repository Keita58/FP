public class Alumne implements Comparable {

    private int nom;
    private int velocitat;
    
    public Alumne(int nom, int velocitat) {
        this.nom = nom;
        this.velocitat = velocitat;
    }

    public int getNom() {
        return nom;
    }

    public void setNom(int nom) {
        this.nom = nom;
    }

    public int getVelocitat() {
        return velocitat;
    }

    public void setVelocitat(int velocitat) {
        this.velocitat = velocitat;
    }

    @Override
    public String toString() {
        return "Alumne [nom=" + nom + ", velocitat=" + velocitat + "]";
    }

    @Override
    public int compareTo(Object o) {
        
        return ((Alumne) o).getVelocitat() - this.getVelocitat();
    }    
}
