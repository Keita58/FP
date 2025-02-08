package DAO;

import entity.Jugadors;

import java.io.Serializable;
import java.util.List;

public interface IJugadorDAO extends IGenericDAO<Jugadors, Integer>, Serializable {
    List<Jugadors> getJugadorsAmbPersonatges();
}
