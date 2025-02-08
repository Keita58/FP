package DAO;

import entity.Jugadors;

import java.util.ArrayList;
import java.util.List;

public class JugadorDAO extends GenericDAO<Jugadors, Integer> implements IJugadorDAO{

    public List<Jugadors> getJugadorsAmbPersonatges() {

        List<Jugadors> jugadors = (List<Jugadors>) this.findAll();
        List<Jugadors> jugadorsAmbPersonatges = new ArrayList<>();
        for (Object jugador : jugadors) {
            Jugadors j = (Jugadors) jugador;
            if(j.getPersonatgeDelJugador() != null)
                jugadorsAmbPersonatges.add(j);
        }
        return jugadorsAmbPersonatges;
    }
}
