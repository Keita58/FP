package Pokemon.Main;

import java.util.Iterator;
import java.util.List;

import Pokemon.Entity.Pokemon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainPokemon {
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pokemon");
		EntityManager entityManager = factory.createEntityManager();

		entityManager.getTransaction().begin(); //Creem una transacció per evitar problemes a la base de dades
		
		Pokemon a1 = new Pokemon("Angel Beats!", "Adolescents morts que es desperten en una escola", false);
		Pokemon a2 = new Pokemon("Inferno Cop", "Curts fets pels dissenyadors de Gainax", true);

		entityManager.persist(a1);
		entityManager.persist(a2);
		entityManager.getTransaction().commit(); //Enviem la informació a la base de dades una vegada confirmem que la info és correcta

		entityManager.getTransaction().begin();
		Pokemon a3 = entityManager.find(Pokemon.class, 1);
		System.out.println(a3.toString());

		a3.setNom("Update Anime");
		entityManager.merge(a3); //Actualitzem la informació de la base de dades amb el merge
		entityManager.getTransaction().commit(); //Enviem l'actualització a la base de dades

		entityManager.getTransaction().begin();
		List<Pokemon> aTots = entityManager.createQuery("Select a from Pokemon a", Pokemon.class).getResultList(); //Passem tota la info de la taula a la llista
		Iterator<Pokemon> it = aTots.iterator();

		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
		
		Pokemon a4 = entityManager.find(Pokemon.class, 1);
		entityManager.remove(a4); //Eliminem la fila que hem buscat
		entityManager.getTransaction().commit();

		entityManager.getTransaction().begin();
		List<Pokemon> aTots2 = entityManager.createQuery("Select a from Pokemon a", Pokemon.class).getResultList(); //Passem tota la info de la taula a la llista
		Iterator<Pokemon> it2 = aTots2.iterator();

		while(it2.hasNext()) {
			System.out.println(it2.next().toString());
		}

		entityManager.close(); //Una vegada hem fet totes les busques a la base de dades tanquem la sessió
	}
}
