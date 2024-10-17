package Act2;

import java.util.concurrent.Callable;

public class Performer implements Callable<Boolean>{

    Note[] notes;
    int channel;

    public Performer(Note[] notes, int channel) {
        this.notes = notes;
        this.channel = channel;
    }

    @Override
    public Boolean call() throws Exception {
        int i = 0;
        while(true && i < notes.length) {
            MidiPlayer.play(this.channel, notes[i]);
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MidiPlayer.stop(this.channel, notes[i]);
            i++;
        }
        return true;
    }
}
