package Examen.services;

import Examen.entity.Jugadors;
import Examen.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JugadorService {
    @Autowired
    JugadorRepository repositori;


    public Jugadors findById(Integer id) {
        return repositori.findById(id).orElse(null);
    }

    public List<Jugadors> findAll() {
        return repositori.findAll();
    }

    public void delete(Integer id) {

        repositori.deleteById(id);
    }

    public void deleteAll() {

        repositori.deleteAllInBatch();
    }

    public Jugadors insertar(Jugadors j) {
        return repositori.save(j);
    }

    public Jugadors editar(Jugadors j) {
        return repositori.save(j);
    }

    public List<Jugadors> getJugadorsAmbPersonatges() {
        return repositori.findByPersonatgeDelJugadorNotEmpty();
    }
}
