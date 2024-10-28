package Anime.Main;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import Anime.Entity.Anime;

public class MainAnime {
	static SessionFactory sessionFactory;
	static ServiceRegistry serviceRegistry;
	static Session session; 
	
	public static synchronized SessionFactory getSessionFactory() {
		//sessionFactory es un Singleton (classe que nomes pot tenir un objecte instanciat)
		try 
		{
			if (sessionFactory == null) 
			{
				StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
				Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
				sessionFactory = metaData.getSessionFactoryBuilder().build();
			}
			return sessionFactory;
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
		
	}

	public static void main(String[] args) {
		
		session = getSessionFactory().openSession();
		session.getTransaction().begin(); //Creem una transacció per evitar problemes a la base de dades
		
		Anime a1 = new Anime("Angel Beats!", "Adolescents morts que es desperten en una escola", false);
		Anime a2 = new Anime("Inferno Cop", "Curts fets pels dissenyadors de Gainax", true);

		session.persist(a1);
		session.persist(a2);
		session.getTransaction().commit(); //Enviem la informació a la base de dades una vegada confirmem que la info és correcta

		session.getTransaction().begin();
		Anime a3 = session.find(Anime.class, 1);
		System.out.println(a3.toString());

		a3.setTitol("Update Anime");
		session.merge(a3); //Actualitzem la informació de la base de dades amb el merge
		session.getTransaction().commit(); //Enviem l'actualització a la base de dades

		session.getTransaction().begin();
		List<Anime> aTots = session.createQuery("Select a from Anime a", Anime.class).getResultList(); //Passem tota la info de la taula a la llista
		Iterator<Anime> it = aTots.iterator();

		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
		
		Anime a4 = session.find(Anime.class, 1);
		session.remove(a4); //Eliminem la fila que hem buscat
		session.getTransaction().commit();

		session.getTransaction().begin();
		List<Anime> aTots2 = session.createQuery("Select a from Anime a", Anime.class).getResultList(); //Passem tota la info de la taula a la llista
		Iterator<Anime> it2 = aTots2.iterator();

		while(it2.hasNext()) {
			System.out.println(it2.next().toString());
		}

		session.close(); //Una vegada hem fet totes les busques a la base de dades tanquem la sessió
	}

}
