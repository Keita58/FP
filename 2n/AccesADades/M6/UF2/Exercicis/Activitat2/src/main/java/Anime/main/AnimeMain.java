package Anime.main;

import java.util.List;
import java.util.Set;

import Anime.entities.Anime;
import Anime.entities.Comandes;
import Anime.entities.Personatges;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AnimeMain {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("animeDatabase");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        Anime anime = new Anime("Jojo", "Aventures màgiques");
        Anime anime1 = new Anime("Jojo2", "Aventures màgiques 2");
        Personatges charac = new Personatges("Jotaro", "Humà");
        Personatges charac1 = new Personatges("Joseph", "Humà");
        Comandes comanda = new Comandes();
        comanda.setPagat(true);
        anime.setPersonatges(List.of(charac));
        anime1.setPersonatges(List.of(charac1, charac));
        anime.setComandes(Set.of(comanda));
        entityManager.merge(anime);
        entityManager.merge(anime1);
        entityManager.persist(comanda);
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();

        List<Anime> result = (List<Anime>) entityManager.createQuery("select a from Anime a", Anime.class).getResultList();
        System.out.println(result);

        entityManager.close();
	}
}
