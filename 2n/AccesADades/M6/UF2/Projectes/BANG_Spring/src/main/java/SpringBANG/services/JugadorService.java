package SpringBANG.services;

import SpringBANG.entity.Jugadors;
import SpringBANG.entity.Rol;
import SpringBANG.entity.Rols;
import SpringBANG.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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

    public List<Jugadors> getJugadorsAmbPersonatgesVida(){
        return repositori.findByPersonatgeDelJugadorBalesGreaterThan(0);
    }

    public List<Jugadors> getJugadorsAmbPersonatgesVidaAltres(Jugadors jugador) {
        return repositori.findByNomJugadorIsNotAndPersonatgeDelJugadorBalesGreaterThan(jugador.getNom(), 0);
    }

    public List<Jugadors> retornarJugadorsOrdenats() {
        return repositori.findAllByOrderByPosicioJugadorAsc();
    }

    public List<Jugadors> getJugadorsRolsVida(Rols rol) {
       return repositori.findByRolJugadorAndPersonatgeDelJugadorBalesGreaterThan(rol, 0);
    }

    public Jugadors getJugadorDelNom(String nomJugador) {
        return repositori.findByNomJugador(nomJugador);
    }

    public Jugadors getJugadorPerId(Integer id) {
        return repositori.findByIdJugador(id);
    }
}
