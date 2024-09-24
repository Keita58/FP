package Ex2;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import Ex1.Anime;

public class main {

	public static void main(String[] args) {
		
		Comparator<Anime> comparadorAny = new Comparator<>() {

			@Override
			public int compare(Anime o1, Anime o2) {
				return Integer.compare(o1.getAnyEstrena(), o2.getAnyEstrena());
			}
			
		};
		
		SortedSet<Anime> llistaAnime = new TreeSet<>(comparadorAny);
		
		llistaAnime.add(new Anime("Angel Beats!", 2010, 21));
		llistaAnime.add(new Anime("Evangelion", 1995, 25));
		llistaAnime.add(new Anime("Inazuma Eleven", 2008, 20));
		llistaAnime.add(new Anime("Death Note", 2006, 18));
		
		System.out.println(llistaAnime);
	}

}
