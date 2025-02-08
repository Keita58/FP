package SpringBANG.repository;

import SpringBANG.entity.Cartes;
import SpringBANG.entity.Jugadors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CartesRepository extends JpaRepository<Cartes, Integer> {

    List<Cartes> findByJugadorQueTeLesCartes(Jugadors jugador);

    List<Cartes> findByJugadorQueTeLesCartesAndCartaTipusCarta_Nom(Jugadors jugador, String cartaTipusCartaNom);
}
