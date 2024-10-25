public class Michibi implements Runnable {

    @Override
    public void run() {
        try {
            while (true) {
                GameController.incrementarMichitokens(1);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            
        }
    }
}
