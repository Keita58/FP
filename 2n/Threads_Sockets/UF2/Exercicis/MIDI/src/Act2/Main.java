package Act2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main
{
	public static void main(String[] args)
	{
		Note[] trumpet57 =
			{
				//1
					new Note(Note.Frequency.Bb4, Note.Duration.blanca),
					new Note(Note.Frequency.SILENCE, Note.Duration.corchea),
					new Note(Note.Frequency.F4, Note.Duration.corchea),
					new Note(Note.Frequency.F4, Note.Duration.corchea),
					new Note(Note.Frequency.Bb4, Note.Duration.corchea),
				//2
					new Note(Note.Frequency.Ab4, Note.Duration.semicorchea),
					new Note(Note.Frequency.Gb4, Note.Duration.semicorchea),
					new Note(Note.Frequency.Ab4, Note.Duration.corchea+Note.Duration.blanca),
					new Note(Note.Frequency.SILENCE, Note.Duration.negra),
				//3
					new Note(Note.Frequency.Bb4, Note.Duration.blanca),
					new Note(Note.Frequency.SILENCE, Note.Duration.corchea),
					new Note(Note.Frequency.Gb4, Note.Duration.corchea),
					new Note(Note.Frequency.Gb4, Note.Duration.corchea),
					new Note(Note.Frequency.Bb4, Note.Duration.corchea),
				//4
					new Note(Note.Frequency.A4, Note.Duration.semicorchea),
					new Note(Note.Frequency.G4, Note.Duration.semicorchea),
					new Note(Note.Frequency.A4, Note.Duration.corchea+Note.Duration.blanca),
					new Note(Note.Frequency.SILENCE, Note.Duration.negra),
				//5
					new Note(Note.Frequency.Db5, Note.Duration.blanca),
					new Note(Note.Frequency.Db5, Note.Duration.blanca),
				//6
					new Note(Note.Frequency.Db5, Note.Duration.blanca),
					new Note(Note.Frequency.Db5, Note.Duration.blanca),
				//7
					new Note(Note.Frequency.Bb4, Note.Duration.negra),
					new Note(Note.Frequency.F4, Note.Duration.negra+Note.Duration.corchea),
					new Note(Note.Frequency.Bb4, Note.Duration.corchea),
					new Note(Note.Frequency.Bb4, Note.Duration.semicorchea),
					new Note(Note.Frequency.C5, Note.Duration.semicorchea),
					new Note(Note.Frequency.D5, Note.Duration.semicorchea),
					new Note(Note.Frequency.Eb5, Note.Duration.semicorchea),
					
				//8
					new Note(Note.Frequency.F5, Note.Duration.redonda),
				//9
					new Note(Note.Frequency.Bb4, Note.Duration.negra),
					new Note(Note.Frequency.F4, Note.Duration.negra+Note.Duration.corchea),
					new Note(Note.Frequency.Bb4, Note.Duration.corchea),
					new Note(Note.Frequency.Bb4, Note.Duration.semicorchea),
					new Note(Note.Frequency.C5, Note.Duration.semicorchea),
					new Note(Note.Frequency.D5, Note.Duration.semicorchea),
					new Note(Note.Frequency.Eb5, Note.Duration.semicorchea),
				//10
					new Note(Note.Frequency.F5, Note.Duration.redonda),
				//11
					new Note(Note.Frequency.Bb4, Note.Duration.negra),
					new Note(Note.Frequency.F4, Note.Duration.negra+Note.Duration.corchea),
					new Note(Note.Frequency.Bb4, Note.Duration.corchea),
					new Note(Note.Frequency.Bb4, Note.Duration.semicorchea),
					new Note(Note.Frequency.C5, Note.Duration.semicorchea),
					new Note(Note.Frequency.D5, Note.Duration.semicorchea),
					new Note(Note.Frequency.Eb5, Note.Duration.semicorchea),
				//12
					new Note(Note.Frequency.F5, Note.Duration.blanca),
					new Note(Note.Frequency.SILENCE, Note.Duration.corchea),
					new Note(Note.Frequency.F5, Note.Duration.corchea+Note.Duration.semicorchea),
					//Truqui perquè fer triplets, jaja, sort.
					new Note(Note.Frequency.F5, Note.Duration.semicorchea),
					new Note(Note.Frequency.Gb5, Note.Duration.semicorchea),
					new Note(Note.Frequency.Ab5, Note.Duration.semicorchea),
				//13
					new Note(Note.Frequency.Bb5, Note.Duration.blanca),
					//truqui perquè triplets
					new Note(Note.Frequency.SILENCE, Note.Duration.negra),
					new Note(Note.Frequency.Bb5, Note.Duration.semicorchea),
					new Note(Note.Frequency.Bb5, Note.Duration.semicorchea),
					new Note(Note.Frequency.Ab5, Note.Duration.semicorchea),
					new Note(Note.Frequency.Gb5, Note.Duration.semicorchea),
				//14
					new Note(Note.Frequency.Ab5, Note.Duration.corchea+Note.Duration.semicorchea),
					new Note(Note.Frequency.Gb5, Note.Duration.semicorchea),
					new Note(Note.Frequency.F5, Note.Duration.blanca),
					new Note(Note.Frequency.F5, Note.Duration.negra),
				//15
					new Note(Note.Frequency.Eb5, Note.Duration.corchea),
					new Note(Note.Frequency.Eb5, Note.Duration.semicorchea),
					new Note(Note.Frequency.F5, Note.Duration.semicorchea),
					new Note(Note.Frequency.Gb5, Note.Duration.blanca),
					new Note(Note.Frequency.F5, Note.Duration.corchea),
					new Note(Note.Frequency.Eb5, Note.Duration.corchea),
				//16
					new Note(Note.Frequency.Db5, Note.Duration.corchea),
					new Note(Note.Frequency.Db5, Note.Duration.semicorchea),
					new Note(Note.Frequency.Eb5, Note.Duration.semicorchea),
					new Note(Note.Frequency.F5, Note.Duration.blanca),
					new Note(Note.Frequency.Eb5, Note.Duration.corchea),
					new Note(Note.Frequency.Db5, Note.Duration.corchea),
				//17
					new Note(Note.Frequency.C5, Note.Duration.corchea),
					new Note(Note.Frequency.C5, Note.Duration.semicorchea),
					new Note(Note.Frequency.D5, Note.Duration.semicorchea),
					new Note(Note.Frequency.E5, Note.Duration.blanca),
					new Note(Note.Frequency.G5, Note.Duration.negra),
				//18
					new Note(Note.Frequency.F5, Note.Duration.corchea),
					new Note(Note.Frequency.F4, Note.Duration.semicorchea),
					new Note(Note.Frequency.F4, Note.Duration.semicorchea),
					new Note(Note.Frequency.F4, Note.Duration.corchea),
					new Note(Note.Frequency.F4, Note.Duration.semicorchea),
					new Note(Note.Frequency.F4, Note.Duration.semicorchea),
					new Note(Note.Frequency.F4, Note.Duration.corchea),
					new Note(Note.Frequency.F4, Note.Duration.semicorchea),
					new Note(Note.Frequency.F4, Note.Duration.semicorchea),
					new Note(Note.Frequency.F4, Note.Duration.corchea),
					new Note(Note.Frequency.F4, Note.Duration.corchea)
				//new Note(Note.Frequency.SILENCE, Note.Duration.redonda*12),
			};
		
		Note[] trombone58 =
			{
				//1
					new Note(Note.Frequency.D4, Note.Duration.redonda),
				//2
					new Note(Note.Frequency.C4, Note.Duration.redonda),
				//3
					new Note(Note.Frequency.Db4, Note.Duration.redonda),
				//4
					new Note(Note.Frequency.C4, Note.Duration.redonda),
				//5
					new Note(Note.Frequency.F4, Note.Duration.redonda),
				//6
					new Note(Note.Frequency.F4, Note.Duration.redonda),
				//7
					new Note(Note.Frequency.SILENCE, Note.Duration.redonda),
				//8
					new Note(Note.Frequency.SILENCE, Note.Duration.corchea),
					new Note(Note.Frequency.Bb4, Note.Duration.corchea),
					new Note(Note.Frequency.Bb4, Note.Duration.semicorchea),
					new Note(Note.Frequency.C5, Note.Duration.semicorchea),
					new Note(Note.Frequency.D5, Note.Duration.semicorchea),
					new Note(Note.Frequency.Eb5, Note.Duration.semicorchea),
					new Note(Note.Frequency.F5, Note.Duration.blanca),
				//9
					new Note(Note.Frequency.SILENCE, Note.Duration.redonda),
				//10
					new Note(Note.Frequency.SILENCE, Note.Duration.corchea),
					new Note(Note.Frequency.Bb4, Note.Duration.corchea),
					new Note(Note.Frequency.Bb4, Note.Duration.semicorchea),
					new Note(Note.Frequency.C5, Note.Duration.semicorchea),
					new Note(Note.Frequency.D5, Note.Duration.semicorchea),
					new Note(Note.Frequency.Eb5, Note.Duration.semicorchea),
					new Note(Note.Frequency.F5, Note.Duration.blanca),
				//11
					new Note(Note.Frequency.D4, Note.Duration.negra+Note.Duration.corchea),
					new Note(Note.Frequency.D4, Note.Duration.semicorchea),
					new Note(Note.Frequency.C4, Note.Duration.semicorchea),
					new Note(Note.Frequency.D4, Note.Duration.negra),
					new Note(Note.Frequency.D4, Note.Duration.semicorchea),
					new Note(Note.Frequency.Eb4, Note.Duration.semicorchea),
					new Note(Note.Frequency.F4, Note.Duration.semicorchea),
					new Note(Note.Frequency.G4, Note.Duration.semicorchea),
				//12
					new Note(Note.Frequency.Ab4, Note.Duration.corchea+Note.Duration.semicorchea),
					new Note(Note.Frequency.Bb4, Note.Duration.semicorchea),
					new Note(Note.Frequency.Bb4, Note.Duration.semicorchea),
					new Note(Note.Frequency.C5, Note.Duration.semicorchea),
					new Note(Note.Frequency.D5, Note.Duration.semicorchea),
					new Note(Note.Frequency.Eb5, Note.Duration.semicorchea),
					//truqui perquè triplets
					new Note(Note.Frequency.F5, Note.Duration.negra+Note.Duration.semicorchea),
					new Note(Note.Frequency.Ab4, Note.Duration.semicorchea),
					new Note(Note.Frequency.Bb4, Note.Duration.semicorchea),
					new Note(Note.Frequency.C5, Note.Duration.semicorchea),
				//13
					new Note(Note.Frequency.Db5, Note.Duration.negra),
					new Note(Note.Frequency.Gb4, Note.Duration.semicorchea),
					new Note(Note.Frequency.Ab4, Note.Duration.semicorchea),
					new Note(Note.Frequency.Bb4, Note.Duration.semicorchea),
					new Note(Note.Frequency.C5, Note.Duration.semicorchea),
					//truqui perquè triplets
					new Note(Note.Frequency.Db5, Note.Duration.negra+Note.Duration.semicorchea),
					new Note(Note.Frequency.Db5, Note.Duration.semicorchea),
					new Note(Note.Frequency.C5, Note.Duration.semicorchea),
					new Note(Note.Frequency.Bb4, Note.Duration.semicorchea),
				//14
					new Note(Note.Frequency.Db5, Note.Duration.corchea+Note.Duration.semicorchea),
					new Note(Note.Frequency.Eb4, Note.Duration.semicorchea),
					new Note(Note.Frequency.Ab4, Note.Duration.corchea),
					new Note(Note.Frequency.Ab4, Note.Duration.semicorchea),
					new Note(Note.Frequency.Gb4, Note.Duration.semicorchea),
					new Note(Note.Frequency.Ab4, Note.Duration.negra),
					//truqui perquè triplets
					new Note(Note.Frequency.Ab4, Note.Duration.semicorchea),
					new Note(Note.Frequency.Gb4, Note.Duration.semicorchea),
					new Note(Note.Frequency.Ab4, Note.Duration.semicorchea),
					new Note(Note.Frequency.SILENCE, Note.Duration.semicorchea),
				//15
					new Note(Note.Frequency.Gb4, Note.Duration.negra+Note.Duration.corchea),
					new Note(Note.Frequency.Gb4, Note.Duration.semicorchea),
					new Note(Note.Frequency.Ab4, Note.Duration.semicorchea),
					new Note(Note.Frequency.Eb4, Note.Duration.negra),
					new Note(Note.Frequency.Ab4, Note.Duration.corchea),
					new Note(Note.Frequency.Gb4, Note.Duration.corchea),
				//16
					new Note(Note.Frequency.F4, Note.Duration.negra),
					new Note(Note.Frequency.F4, Note.Duration.corchea),
					new Note(Note.Frequency.F4, Note.Duration.semicorchea),
					new Note(Note.Frequency.Gb4, Note.Duration.semicorchea),
					new Note(Note.Frequency.Ab4, Note.Duration.negra),
					new Note(Note.Frequency.Gb4, Note.Duration.corchea),
					new Note(Note.Frequency.F4, Note.Duration.corchea),
				//17
					new Note(Note.Frequency.E4, Note.Duration.negra+Note.Duration.corchea),
					new Note(Note.Frequency.E4, Note.Duration.semicorchea),
					new Note(Note.Frequency.F4, Note.Duration.semicorchea),
					new Note(Note.Frequency.G4, Note.Duration.corchea),
					new Note(Note.Frequency.G4, Note.Duration.semicorchea),
					new Note(Note.Frequency.A4, Note.Duration.semicorchea),
					new Note(Note.Frequency.Bb4, Note.Duration.corchea),
					new Note(Note.Frequency.C5, Note.Duration.corchea),
				//18
					new Note(Note.Frequency.A4, Note.Duration.corchea),
					new Note(Note.Frequency.F4, Note.Duration.semicorchea),
					new Note(Note.Frequency.F4, Note.Duration.semicorchea),
					new Note(Note.Frequency.F4, Note.Duration.corchea),
					new Note(Note.Frequency.F4, Note.Duration.semicorchea),
					new Note(Note.Frequency.F4, Note.Duration.semicorchea),
					new Note(Note.Frequency.F4, Note.Duration.corchea),
					new Note(Note.Frequency.F4, Note.Duration.semicorchea),
					new Note(Note.Frequency.F4, Note.Duration.semicorchea),
					new Note(Note.Frequency.F4, Note.Duration.corchea),
					new Note(Note.Frequency.F4, Note.Duration.corchea)
			};
		
		Note[] chorus54 =
			{
				//1
					new Note(Note.Frequency.Bb2, Note.Duration.redonda),
				//2
					new Note(Note.Frequency.Ab2, Note.Duration.redonda),
				//3
					new Note(Note.Frequency.Gb2, Note.Duration.redonda),
				//4
					new Note(Note.Frequency.F2, Note.Duration.redonda),
				//5
					new Note(Note.Frequency.Bb2, Note.Duration.redonda),
				//6
					new Note(Note.Frequency.Bb2, Note.Duration.redonda),
				//7
					new Note(Note.Frequency.Bb2, Note.Duration.redonda),
				//8
					new Note(Note.Frequency.Ab2, Note.Duration.redonda),
				//9
					new Note(Note.Frequency.Gb2, Note.Duration.redonda),
				//10
					new Note(Note.Frequency.F2, Note.Duration.redonda),
				//11
					new Note(Note.Frequency.Bb2, Note.Duration.redonda),
				//12
					new Note(Note.Frequency.Ab2, Note.Duration.redonda),
				//13
					new Note(Note.Frequency.Gb2, Note.Duration.redonda),
				//14
					new Note(Note.Frequency.Db3, Note.Duration.redonda),
				//15
					new Note(Note.Frequency.Cb3, Note.Duration.redonda),
				//16
					new Note(Note.Frequency.Bb2, Note.Duration.redonda),
				//17
					new Note(Note.Frequency.C3, Note.Duration.redonda),
				//18
					new Note(Note.Frequency.A2, Note.Duration.corchea),
					new Note(Note.Frequency.A2, Note.Duration.semicorchea),
					new Note(Note.Frequency.A2, Note.Duration.semicorchea),
					new Note(Note.Frequency.Ab2, Note.Duration.corchea),
					new Note(Note.Frequency.Ab2, Note.Duration.semicorchea),
					new Note(Note.Frequency.Ab2, Note.Duration.semicorchea),
					new Note(Note.Frequency.G2, Note.Duration.corchea),
					new Note(Note.Frequency.G2, Note.Duration.semicorchea),
					new Note(Note.Frequency.G2, Note.Duration.semicorchea),
					new Note(Note.Frequency.Gb2, Note.Duration.corchea),
					new Note(Note.Frequency.Gb2, Note.Duration.semicorchea),
					new Note(Note.Frequency.Gb2, Note.Duration.semicorchea),
			};

		ExecutorService executor = Executors.newFixedThreadPool(4);
		List<Future<Boolean>> futurs = new ArrayList<>();
		Conductor cond = new Conductor(trumpet57, 80, 40);
		futurs.add(executor.submit(new Performer(trumpet57, 0, cond)));
		futurs.add(executor.submit(new Performer(trombone58, 1, cond)));
		futurs.add(executor.submit(new Performer(chorus54, 2, cond)));
		futurs.add(executor.submit(cond));
		executor.shutdown();

		for(Future<Boolean> futur : futurs) {
			try {
				futur.get();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
