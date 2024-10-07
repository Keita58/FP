package Ex1;

import java.util.SortedSet;
import java.util.TreeSet;

public class main {

	public static void main(String[] args) {
		
		SortedSet<Anime> llistaAnime = new TreeSet<>();
		llistaAnime.add(new Anime("Angel Beats!", 2010, 21));
		llistaAnime.add(new Anime("Evangelion", 1995, 25));
		llistaAnime.add(new Anime("Inazuma Eleven", 2008, 20));
		llistaAnime.add(new Anime("Death Note", 2006, 18));
		
		System.out.println(llistaAnime);
	}
}
