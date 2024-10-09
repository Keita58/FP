package Act2;
import java.util.Random;

public class Consumidor implements Runnable{

    private TCGStore tenda;

    private String colorVermell = "\u001B[31m";
    private String reset = "\u001B[0m";

    public Consumidor(TCGStore tenda) {
        this.tenda = tenda;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Random r = new Random();
                String carta = this.tenda.Consumir();
                System.out.println(colorVermell + "El consumidor ha comprat la carta " + carta + reset);
                Thread.sleep(r.nextInt(6)*1000);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

}
