package main;

import DAO.IJugadorDAO;
import DAO.IPersonatgeDAO;
import entity.Genere;
import entity.Jugadors;
import entity.Personatges;
import factory.DAOFactory;
import factory.DAOFactoryImpl;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Examen {
    public static void main(String[] args) {
        DAOFactory daoFactory = DAOFactoryImpl.getFactory("MySQL");

        IJugadorDAO jDAO = (IJugadorDAO) daoFactory.create("jugador");
        IPersonatgeDAO pDAO = (IPersonatgeDAO) daoFactory.create("personatge");

        Personatges bart = new Personatges("Bart Cassidy", Genere.MASCULI, true);
        Personatges black = new Personatges("Black Jack", Genere.MASCULI, false);
        Personatges calamity = new Personatges("Calamity Janet", Genere.MASCULI, false);
        Personatges gringo = new Personatges("El Gringo", Genere.MASCULI, true);
        Personatges jesse = new Personatges("Jesse Jones", Genere.FEMENI, true);
        pDAO.create(bart);
        pDAO.create(black);
        pDAO.create(calamity);
        pDAO.create(gringo);
        pDAO.create(jesse);

        HashSet<Jugadors> ju = new HashSet<>();
        for(int i = 1; i < 6; i++) {
            Jugadors j = new Jugadors("Jugador" + i);
            jDAO.create(j);
            ju.add(j);
            jDAO.update(j);
        }

        List<Jugadors> jugadorsList = jDAO.findAll();
        List<Personatges> personatgesList = pDAO.findAll();

        int i = 0;

        jugadorsList.get(0).setPersonatgeDelJugador(Set.of(personatgesList.get(0), personatgesList.get(1)));
        jDAO.update(jugadorsList.get(0));

        personatgesList.get(0).getPersonatgeAmbJugador().add(jugadorsList.get(0));
        personatgesList.get(1).getPersonatgeAmbJugador().add(jugadorsList.get(0));
        pDAO.update(personatgesList.get(0));
        pDAO.update(personatgesList.get(1));

        jugadorsList.get(1).setPersonatgeDelJugador(Set.of(personatgesList.get(1)));
        jDAO.update(jugadorsList.get(1));
        personatgesList.get(1).getPersonatgeAmbJugador().add(jugadorsList.get(1));
        pDAO.update(personatgesList.get(1));

        jugadorsList.get(2).setPersonatgeDelJugador(Set.of(personatgesList.get(2)));
        jDAO.update(jugadorsList.get(2));
        personatgesList.get(2).getPersonatgeAmbJugador().add(jugadorsList.get(2));
        pDAO.update(personatgesList.get(2));

        jugadorsList.get(3).setPersonatgeDelJugador(Set.of(personatgesList.get(3)));
        jDAO.update(jugadorsList.get(3));
        personatgesList.get(3).getPersonatgeAmbJugador().add(jugadorsList.get(3));
        pDAO.update(personatgesList.get(3));

        jugadorsList.get(4).setPersonatgeDelJugador(Set.of(personatgesList.get(4)));
        jDAO.update(jugadorsList.get(4));
        personatgesList.get(4).getPersonatgeAmbJugador().add(jugadorsList.get(4));
        pDAO.update(personatgesList.get(4));

        FinalPartida();
    }

    public static void FinalPartida(){
        DAOFactory daoFactory = DAOFactoryImpl.getFactory("MySQL");

        IJugadorDAO jDao = (IJugadorDAO) daoFactory.create("jugador");

        List<Jugadors> jList = jDao.getJugadorsAmbPersonatges();

        for (Jugadors j : jList) {
            j.setGuanyats(j.getGuanyats() + 1);
            j.setPunts(j.getPunts() + (20 * j.getPersonatgeDelJugador().size()));
            jDao.update(j);
        }
        System.out.println("ACABA EL JOC");
    }
}
