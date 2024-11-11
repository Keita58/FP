package Examen.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import Examen.entities.Armes;
import Examen.entities.Mortalitat;
import Examen.entities.Personatge;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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
        
        List<Armes> aux = new ArrayList<>();
        aux.add(an);
        a.setArmes(aux);
        
        List<Armes> aux1 = new ArrayList<>();
        aux1.add(bn);
        b.setArmes(aux1);
        
        List<Armes> aux2 = new ArrayList<>();
        aux2.add(cn);
        c.setArmes(aux2);
        
        List<Armes> aux3 = new ArrayList<>();
        aux3.add(dn);
        d.setArmes(aux3);
        
        List<Armes> aux4 = new ArrayList<>();
        aux4.add(an);
        aux4.add(en);
        e.setArmes(aux4);
        
        entityManager.merge(a);
        entityManager.merge(b);
        entityManager.merge(c);
        entityManager.merge(d);
        entityManager.merge(e);
        entityManager.getTransaction().commit();
        
        entityManager.getTransaction().begin();

        List<Personatge> result = (List<Personatge>) entityManager.createQuery("select a from Personatge a", Personatge.class).getResultList();
        System.out.println(result);
        
        List<Armes> result2 = (List<Armes>) entityManager.createQuery("select a from Armes a", Armes.class).getResultList();
        System.out.println(result2);

        entityManager.close();
	}

}
