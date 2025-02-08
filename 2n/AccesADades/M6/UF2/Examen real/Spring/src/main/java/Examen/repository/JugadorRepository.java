package Examen.repository;

import Examen.entity.Jugadors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JugadorRepository extends JpaRepository<Jugadors, Integer> {
    List<Jugadors> findByPersonatgeDelJugadorNotEmpty();
}
