package Act2;

import java.util.concurrent.Callable;

public class Performer implements Runnable{

    Note[] notes;
    int bpm;
    int duracio;

    public Performer(Note[] notes, int bpm, int duracio) {
        this.notes = notes;
        this.bpm = bpm;
        this.duracio = duracio;
    }

    @Override
    public Boolean call() throws Exception {
        int tick = ((1000*duracio)/bpm)/Note.Duration.negra; //Duració bàsica d'una semifusa
        for(int i = 0; i < notes.length; i++) {
            MidiPlayer.play(0, notes[i]);
            for(int j = 0; j < notes[i].getDuration(); j++) {
                Thread.sleep(tick);
            }
            MidiPlayer.stop(0, notes[i]);
            System.out.println("Nota");
        }
        return true;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}
