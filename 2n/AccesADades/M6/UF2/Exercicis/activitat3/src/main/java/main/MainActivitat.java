package main;

import java.util.List;
import java.util.Set;

import dao.IAnimeDAO;
import dao.IComandesDAO;
import dao.IPersonatgesDAO;
import dao.IPlataformaDAO;
import entity.Anime;
import entity.Comandes;
import entity.Personatges;
import entity.Plataforma;
import factory.DAOFactory;
import factory.DAOFactoryImplementation;

public class MainActivitat {

    public static void main(String[] args) {
        
        Anime a1 = new Anime("Angel Beats!", "Adolescents morts que no van al cel", false);
        Anime a2 = new Anime("Jojo's Bizarre Adventure", "Adolescents que van a matar a un vampir", true);
        Anime a3 = new Anime("Inazuma Eleven", "Adolescents que tenen un equip de futbol", true);

        Comandes c1 = new Comandes(false);
        Comandes c2 = new Comandes(true);
        Comandes c3 = new Comandes(false);

        Personatges p1 = new Personatges("Mark Evans", "Mà celestial");
        Personatges p2 = new Personatges("Otonashi", "Metge");
        Personatges p3 = new Personatges("Jotaro", "Star Platinum");

        Plataforma pl1 = new Plataforma("Netflix", "netflix.com", "108.175.32.0/20");
        Plataforma pl2 = new Plataforma("Amazon Prime Video", "primevideo.com", "3.4.12.4/32");
        Plataforma pl3 = new Plataforma("Crunchiroll", "crunchiroll.com", "172.64.153.54");

        DAOFactoryImplementation dao = new DAOFactoryImplementation();
        DAOFactory daoFactory = dao.getFactory("Anime");

        IAnimeDAO animeDAO = (IAnimeDAO)daoFactory.create("anime");
        IComandesDAO comandesDAO = (IComandesDAO)daoFactory.create("comandes");
        IPersonatgesDAO personatgesDAO = (IPersonatgesDAO)daoFactory.create("personatges");
        IPlataformaDAO plataformaDAO = (IPlataformaDAO)daoFactory.create("plataforma");

        /**
         * Quan creem els ítems a la base de dades d'aquesta manera ja es fan un persist automàticament
         * perquè la seva implementació està creada a GenericDAO.
         */
        animeDAO.create(a1);
        animeDAO.create(a2);
        animeDAO.create(a3);

        comandesDAO.create(c1);
        comandesDAO.create(c2);
        comandesDAO.create(c3);

        personatgesDAO.create(p1);
        personatgesDAO.create(p2);
        personatgesDAO.create(p3);

        plataformaDAO.create(pl1);
        plataformaDAO.create(pl2);
        plataformaDAO.create(pl3);

        List<Anime> llistaAnime = animeDAO.findAll();
        for (Object anime : llistaAnime) {
            Anime a = (Anime) anime;
            System.out.println(a.toString());
        }
        System.out.println();

        List<Comandes> llistaComandes = comandesDAO.findAll();
        for (Object com : llistaComandes) {
            Comandes c = (Comandes) com;
            System.out.println(c.toString());
        }
        System.out.println();

        List<Personatges> llistaPer = personatgesDAO.findAll();
        for (Object per : llistaPer) {
            Personatges p = (Personatges) per;
            System.out.println(p.toString());
        }
        System.out.println();

        List<Plataforma> llistaPla = plataformaDAO.findAll();
        for (Plataforma pla : llistaPla) {
            Plataforma p = (Plataforma) pla;
            System.out.println(p.toString());
        }
        System.out.println();

        a1.setPlataforma(pl1);
        a2.setPlataforma(pl2);
        a3.setPlataforma(pl3);

        animeDAO.update(a1);
        animeDAO.update(a2);
        animeDAO.update(a3);

        List<Plataforma> llistaPla2 = plataformaDAO.findAll();
        for (Object pla : llistaPla2) {
            Plataforma p = (Plataforma) pla;
            System.out.println(p.toString());
            System.out.print("Anime relacionat: ");
            Anime a = p.getAnime();
            System.out.println(a.toString());
            System.out.println();
        }

        c1.setAnimesComprats(Set.of(a1));
        c2.setAnimesComprats(Set.of(a1));
        c3.setAnimesComprats(Set.of(a2));

        comandesDAO.update(c1);
        comandesDAO.update(c2);
        comandesDAO.update(c3);

        List<Comandes> llistaComandes2 = comandesDAO.findAll();
        for (Object comandes : llistaComandes2) {
            Comandes c = (Comandes) comandes;
            System.out.println(c.toString());
            System.out.println("Anime comprat en aquesta comanda: ");
            Set<Anime> anime = c.getAnimesComprats();
            for (Object anime2 : anime) {
                Anime aux = (Anime) anime2;
                System.out.println(aux.toString());
            }
            System.out.println();    
        }

        p1.setAnime(a1);
        p2.setAnime(a1);
        p3.setAnime(a1);

        Personatges p4 = new Personatges("Goku", "Ki");
        Personatges p5 = new Personatges("Kirito", "Ninja");
        
        personatgesDAO.create(p4);
        personatgesDAO.create(p5);

        p4.setAnime(a2);
        p5.setAnime(a2);

        personatgesDAO.update(p1);
        personatgesDAO.update(p2);
        personatgesDAO.update(p3);
        personatgesDAO.update(p4);
        personatgesDAO.update(p5);

        List<Comandes> llistaComandes3 = comandesDAO.findAll();
        for (Object comandes : llistaComandes3) {
            Comandes c = (Comandes) comandes;
            System.out.println(c.toString());
            System.out.println("Anime comprat en aquesta comanda: ");
            Set<Anime> anime = c.getAnimesComprats();
            for (Object anime2 : anime) {
                Anime aux = (Anime) anime2;
                System.out.println(aux.toString());
                System.out.println("Personatges d'aquest anime: ");
                Set<Personatges> p = aux.getPersonatgesDeLanime();
                for (Object pe : p) {
                    Personatges per = (Personatges) pe;
                    System.out.println(per.toString());
                }
            }
            System.out.println();    
        }
    }
}
