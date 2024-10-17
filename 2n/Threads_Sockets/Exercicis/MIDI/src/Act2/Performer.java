package Act2;

import java.util.concurrent.Callable;

public class Performer implements Callable<Boolean>{

    Note[] notes;
    int channel;
    Conductor cond;

    public Performer(Note[] notes, int channel, Conductor con) {
        this.notes = notes;
        this.channel = channel;
        this.cond = con;
    }

    @Override
    public Boolean call() throws Exception {
        int i = 0;
        while(true && i < notes.length) {
            MidiPlayer.play(this.channel, notes[i]);
            try {
                for(int j = 0; j < notes[i].getDuration(); j++) {
                    synchronized (this.cond) {
                        cond.wait();
                        System.out.println("Notes - " + Thread.currentThread());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MidiPlayer.stop(this.channel, notes[i]);
            i++;
        }
        return true;
    }
}
