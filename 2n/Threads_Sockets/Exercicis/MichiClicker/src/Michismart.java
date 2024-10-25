import java.util.concurrent.Semaphore;
import java.util.Map;

public class Michismart implements Runnable {

    Semaphore sem;
    Map<String, Integer> diccionari;

    public Michismart(Semaphore sem, Map<String, Integer> diccionari) {
        this.sem = sem;
        this.diccionari = diccionari;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            if(diccionari.get("Michismart") > 1)
                diccionari.put("Michismart", (diccionari.get("Michismart") - 1));
            else if (diccionari.get("Michismart") == 1) {
                diccionari.remove("Michismart");
            }
            GameController.incrementarMichitokens(1000);
            sem.release();
        } catch (Exception e) {
            
        }
    }
}
