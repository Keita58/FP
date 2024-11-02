package Anime.main;

import java.math.BigDecimal;
import java.util.ArrayList;

import Anime.entities.Anime;
import Anime.entities.Genere;
import Anime.entities.Personatges;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AnimeMain {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("animeDatabase");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        Anime anime = new Anime("Jojo", "Aventures màgiques", false);
        entityManager.persist(anime);
        anime = new Anime("Jojo2", "Aventures màgiques 2", true);
        entityManager.persist(anime);

        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();

        Personatges charac = new Personatges("Jotaro", "Humà");
        entityManager.persist(charac);
        charac = new Personatges("Joseph", "humà2");
        entityManager.persist(charac);

        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();

        ArrayList<Anime> result = (ArrayList<Anime>) entityManager.createQuery("select a from Anime a", Anime.class).getResultList();
        System.out.println(result);

        ArrayList<Character> result2 = (ArrayList<Character>) entityManager.createQuery("select c from character c", Character.class).getResultList();
        System.out.println(result2);

        entityManager.close();

	}
}
