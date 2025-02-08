package SpringBANG.services;

import SpringBANG.entity.Cartes;
import SpringBANG.entity.Jugadors;
import SpringBANG.repository.CartesRepository;
import SpringBANG.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaService {
    @Autowired
    CartesRepository cartesRepository;
    @Autowired
    private JugadorRepository jugadorRepository;

    public List<Cartes> findAll(){
        return cartesRepository.findAll();
    }

    public Cartes editar(Cartes c) {
        return cartesRepository.save(c);
    }

    public List<Cartes> getCartesSenseJugador(){
        return cartesRepository.findByJugadorQueTeLesCartes(null);
    }

    public List<Cartes> getCartesBangSenseJugador(){
        return cartesRepository.findByJugadorQueTeLesCartesAndCartaTipusCarta_Nom(null, "Bang!");
    }

    public List<Cartes> getCartesJugador(Jugadors jugador){
        Jugadors j = jugadorRepository.findByNomJugador(jugador.getNom());
        return cartesRepository.findByJugadorQueTeLesCartes(j);
    }

    public List<Cartes> getCartesFallasteJugador(Jugadors jugador) {
        Jugadors j = jugadorRepository.findByNomJugador(jugador.getNom());
        return cartesRepository.findByJugadorQueTeLesCartesAndCartaTipusCarta_Nom(j, "Has Fallat!");
    }

    public void delete(Integer id) {

        cartesRepository.deleteById(id);
    }

    public void deleteAll() {

        cartesRepository.deleteAll();
    }
}
