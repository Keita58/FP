package Anime.Main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

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
				StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
						.configure("hibernate.cfg.xml").build();
				
				Metadata metaData = new MetadataSources(standardRegistry)
						.getMetadataBuilder()
						.build();
				
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
		
		
	}

}
