package Act1;
public class TCGStore {

    private String[] cartes;
    private int seguent;
    private boolean senseCartes;
    private boolean maximStock;
    private int id;

    private String colorNegre = "\u001B[30m";
    private String reset = "\u001B[0m"; 

    public TCGStore(int mida) {
        this.cartes = new String[mida];
        this.seguent = 0;
        this.senseCartes = true;
        this.maximStock = false;
        this.id = 1;
    }

    public synchronized void Produir(String carta) {
        while(this.maximStock){
            try {
                wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cartes[seguent] = carta + " - Id: " + this.id;
        seguent++;
        this.id++;
        this.senseCartes = false;
        if(seguent == this.cartes.length) 
            this.maximStock = true;
        notifyAll();
        System.out.print(colorNegre + "L'stock de la tenda és: [");
        for(int i = 0; i < seguent; i++) {
            System.out.print(cartes[i] + ", ");
        }
        System.out.print("]" + reset);
        System.out.println();
    }

    public synchronized String Consumir() {
        while(this.senseCartes){
            try {
                wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        seguent--;
        this.maximStock = false;
        if(seguent == 0) 
            this.senseCartes = true;
        notifyAll();
        System.out.print(colorNegre + "L'stock de la tenda és: [");
        for(int i = 0; i < seguent; i++) {
            System.out.print(cartes[i] + ", ");
        }
        System.out.print("]" + reset);
        System.out.println();
        return cartes[seguent];
    }
}
