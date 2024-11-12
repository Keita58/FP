package main;

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
        Anime a3 = new Anime("Inazuma Eleven", "Adolescents que tenen un equip de futbol lluiten contra alienígenes", true);

        Comandes c1 = new Comandes(false);
        Comandes c2 = new Comandes(true);
        Comandes c3 = new Comandes(false);

        Personatges p1 = new Personatges("Mark Evans", "Mà celestial");
        Personatges p2 = new Personatges("Otonashi", "Metge");
        Personatges p3 = new Personatges("Jotaro", "Star Platinum");

        Plataforma pl1 = new Plataforma("Netflix", "netflix.com");
        Plataforma pl2 = new Plataforma("Amazon Prime Video", "primevideo.com");
        Plataforma pl3 = new Plataforma("Crunchiroll", "crunchiroll.com");

        DAOFactoryImplementation dao = new DAOFactoryImplementation();
        DAOFactory daoFactory = dao.geFactory("Anime");

        IAnimeDAO animeDAO = (IAnimeDAO)daoFactory.create("anime");
        IComandesDAO comandesDAO = (IComandesDAO)daoFactory.create("comandes");
        IPersonatgesDAO personatgesDAO = (IPersonatgesDAO)daoFactory.create("personatges");
        IPlataformaDAO plataformaDAO = (IPlataformaDAO)daoFactory.create("plataforma");

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

        a1.setPlataforma(pl1);
        a2.setPlataforma(pl2);
        a3.setPlataforma(pl3);

        c1.setAnimesComprats(Set.of(a1));
        c2.setAnimesComprats(Set.of(a1));
        c3.setAnimesComprats(Set.of(a2));

        
    }
}
