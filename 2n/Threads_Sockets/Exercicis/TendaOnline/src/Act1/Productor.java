package Act1;
import java.util.Random;

public class Productor implements Runnable{

    private TCGStore tenda;
    private final String[] cartes = {"Mewtwo & Mew GX", "Double Colourless Energy", "Professor Oak/Professor's Research", "Charizard", "Zoroark GX", "Shaymin EX", "Arceus & Dialga & Palkia GX", "Radiant Greninja", "N", "Pikachu & Zekrom GX"};

    private String colorVerd = "\u001B[32m";
    private String reset = "\u001B[0m";

    public Productor(TCGStore tenda) {
        this.tenda = tenda;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Random r = new Random();
                String carta = cartes[r.nextInt(cartes.length)];
                tenda.Produir(carta);
                System.out.println(colorVerd + "El productor ha creat la carta " + carta + reset);
                Thread.sleep(r.nextInt(3)*1000);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}
