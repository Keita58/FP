package Act1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.sound.midi.Instrument;

public class Main
{
	public static void main(String[] args)
	{
		Note[] arpegio = 
		{
			new Note(Note.Frequency.C4, Note.Duration.quarter),
				new Note(Note.Frequency.D4, Note.Duration.quarter),
				new Note(Note.Frequency.E4, Note.Duration.quarter),
				new Note(Note.Frequency.G4, Note.Duration.quarter),
				new Note(Note.Frequency.C5, Note.Duration.quarter),
				new Note(Note.Frequency.D5, Note.Duration.quarter),
				new Note(Note.Frequency.E5, Note.Duration.quarter),
				new Note(Note.Frequency.G5, Note.Duration.quarter),
				new Note(Note.Frequency.C6, Note.Duration.quarter),
				new Note(Note.Frequency.G5, Note.Duration.quarter),
				new Note(Note.Frequency.E5, Note.Duration.quarter),
				new Note(Note.Frequency.D5, Note.Duration.quarter),
				new Note(Note.Frequency.C5, Note.Duration.quarter),
				new Note(Note.Frequency.G4, Note.Duration.quarter),
				new Note(Note.Frequency.E4, Note.Duration.quarter),
				new Note(Note.Frequency.D4, Note.Duration.quarter),
				
				new Note(Note.Frequency.A3, Note.Duration.quarter),
				new Note(Note.Frequency.B3, Note.Duration.quarter),
				new Note(Note.Frequency.C4, Note.Duration.quarter),
				new Note(Note.Frequency.E4, Note.Duration.quarter),
				new Note(Note.Frequency.A4, Note.Duration.quarter),
				new Note(Note.Frequency.B4, Note.Duration.quarter),
				new Note(Note.Frequency.C5, Note.Duration.quarter),
				new Note(Note.Frequency.E5, Note.Duration.quarter),
				new Note(Note.Frequency.A5, Note.Duration.quarter),
				new Note(Note.Frequency.E5, Note.Duration.quarter),
				new Note(Note.Frequency.C5, Note.Duration.quarter),
				new Note(Note.Frequency.B4, Note.Duration.quarter),
				new Note(Note.Frequency.A4, Note.Duration.quarter),
				new Note(Note.Frequency.E4, Note.Duration.quarter),
				new Note(Note.Frequency.C4, Note.Duration.quarter),
				new Note(Note.Frequency.B3, Note.Duration.quarter)
		};

		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future<Boolean> futur = executor.submit(new Performer(arpegio, 60, 60));
		executor.shutdown();

		try {
			futur.get();
		} 
		catch (ExecutionException e) {
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
