import java.util.Random;
import java.util.concurrent.Callable;

public class Cursa implements Callable<Alumne>{

    Alumne alumne;
    Random r;
    int[] existencies;

    public Cursa(Alumne alumne, int existencies[]) {
        this.alumne = alumne;
        this.r = new Random();
        this.existencies = existencies;
    }

    public void agafarPotenciador() {
        int potenciador = this.r.nextInt(4);

        switch (potenciador) {
            case 0:
                if(this.existencies[0] > 0) {
                    this.alumne.setVelocitat(this.alumne.getVelocitat() + 25);
                    MainExamen.restarExistencies(0);
                }
                else {
                    System.out.println("M'he distret intentat agafar un potenciador petit! (Alumne " + this.alumne.getNom() + ")");
                    this.alumne.setVelocitat(this.alumne.getVelocitat() - 10);
                }
                break;
            case 1:
                if(this.existencies[1] > 0) {
                    this.alumne.setVelocitat(this.alumne.getVelocitat() + 50);
                    MainExamen.restarExistencies(1);
                }
                else {
                    System.out.println("M'he distret intentant agafar un potenciador mitjà! (Alumne " + this.alumne.getNom() + ")");
                    this.alumne.setVelocitat(this.alumne.getVelocitat() - 10);
                }
                break;
            case 2:
                if(this.existencies[2] > 0) {
                    this.alumne.setVelocitat(this.alumne.getVelocitat() + 100);
                    MainExamen.restarExistencies(2);
                }
                else {
                    System.out.println("M'he distret intentant agafar un potenciador gran! (Alumne " + this.alumne.getNom() + ")");
                    this.alumne.setVelocitat(this.alumne.getVelocitat() - 10);
                }
                break;
            case 3:
                if(this.existencies[3] > 0) {
                    this.alumne.setVelocitat(this.alumne.getVelocitat() + 200);
                    MainExamen.restarExistencies(3);
                }
                else {
                    System.out.println("M'he distret intentant agafar un potenciador súper! (Alumne " + this.alumne.getNom() + ")");
                    this.alumne.setVelocitat(this.alumne.getVelocitat() - 10);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public Alumne call() throws Exception {

        boolean sortir = false;

        try {
            while(!sortir) {
                int temps = this.r.nextInt(1, 4);
                Thread.sleep(1000*temps);
                int accio = this.r.nextInt(4);
    
                switch(accio) {
                    case 0:
                    case 1:
                    case 2:
                        agafarPotenciador();
                        break;
                    case 3:
                        System.out.println("He trompejat! Nom: " + this.alumne.getNom());
                        sortir = true;
                        MainExamen.sumaAcabat();
                        break;
                }
            }
        } catch (InterruptedException e) {
            System.out.println("He sigut l'últim en peu! Nom: " + this.alumne.getNom());
        }

        return this.alumne;
    }
}
