package Act2;

import java.util.concurrent.Callable;

public class Conductor implements Callable<Boolean>{

    private Note[] notes;
    private int tick;

    public Conductor(Note[] notes, int bpm, int duracio) {
        this.notes = notes;
        this.tick = ((1000*duracio)/bpm)/Note.Duration.negra; //Duració bàsica d'una semifusa
    }

    @Override
    public Boolean call() throws Exception {
        for(int i = 0; i < notes.length; i++) {
            for(int j = 0; j < Note.Duration.negra; j++) {
                try {
                    Thread.sleep(tick);
                    notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
