package SpringBANG.repository;

import SpringBANG.entity.Jugadors;
import SpringBANG.entity.Rols;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JugadorRepository extends JpaRepository<Jugadors, Integer> {
    List<Jugadors> findAllByOrderByPosicioJugadorAsc();

    List<Jugadors> findByPersonatgeDelJugadorBalesGreaterThan(int i);

    List<Jugadors> findByRolJugadorAndPersonatgeDelJugadorBalesGreaterThan(Rols rol, int i);

    List<Jugadors> findByNomJugadorIsNotAndPersonatgeDelJugadorBalesGreaterThan(String nom, int i);

    Jugadors findByNomJugador(String nom);

    Jugadors findByIdJugador(int idJugador);
}
