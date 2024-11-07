public class Michonk implements Runnable {

    @Override
    public void run() {
        try {
            while (true) {
                GameController.incrementarMichitokens(5);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            
        }
    }


}
