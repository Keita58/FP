package Examen.main;

import java.util.List;
import java.util.Set;

import Examen.entities.Armes;
import Examen.entities.Mortalitat;
import Examen.entities.Personatge;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class MainExamen {

	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("examen");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();
        Personatge a = new Personatge(0, "a", true, 10);
        Personatge b = new Personatge(0, "b", true, 20);
        Personatge c = new Personatge(0, "c", false, 500);
        Personatge d = new Personatge(0, "d", false, 1000);
        Personatge e = new Personatge(0, "e", true, 5);
        
        Armes an = new Armes(0, "an", Mortalitat.MITJÀ, 13, true);
        Armes bn = new Armes(0, "bn", Mortalitat.MITJÀ, 9, true);
        Armes cn = new Armes(0, "cn", Mortalitat.ALT, 30, false);
        Armes dn = new Armes(0, "dn", Mortalitat.BAIX, 1, false);
        Armes en = new Armes(0, "en", Mortalitat.ALT, 50, true);

        entityManager.persist(a);
        entityManager.persist(b);
        entityManager.persist(c);
        entityManager.persist(d);
        entityManager.persist(e);
        entityManager.persist(an);
        entityManager.persist(bn);
        entityManager.persist(cn);
        entityManager.persist(dn);
        entityManager.persist(en);
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
        
        a.getArmes().add(an);
        b.getArmes().add(bn);
        c.getArmes().add(cn);
        d.getArmes().add(dn);
        e.getArmes().add(en);
        e.getArmes().add(an);
        
        entityManager.merge(a);
        entityManager.merge(b);
        entityManager.merge(c);
        entityManager.merge(d);
        entityManager.merge(e);
        entityManager.getTransaction().commit();
        
        entityManager.getTransaction().begin();

        TypedQuery<Personatge> result = entityManager.createQuery("select a from Personatge a", Personatge.class);
        List<Personatge> res = result.getResultList();
        System.out.println(res);
        
        TypedQuery<Armes> result2 = entityManager.createQuery("select a from Armes a", Armes.class);
        List<Armes> res2 = result2.getResultList();
        System.out.println(res2);

        for(Personatge p : res) {
            System.out.print("Personatge: " + p.toString() + " té l'arma -> ");
            Set<Armes> ar = p.getArmes();
            for(Armes arma : ar) {
                if(ar.size() > 1)
                    System.out.print(arma.toString() + ", ");
                else
                    System.out.print(arma.toString());
            }
            System.out.println();
        }

        entityManager.close();
	}
}
