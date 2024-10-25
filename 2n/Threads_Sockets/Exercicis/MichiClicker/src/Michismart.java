public class Michismart implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            GameController.incrementarMichitokens(1000);
        } catch (Exception e) {
            
        }
    }
}
