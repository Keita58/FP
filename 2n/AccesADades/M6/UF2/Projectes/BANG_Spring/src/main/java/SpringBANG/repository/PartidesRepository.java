package SpringBANG.repository;

import SpringBANG.entity.Partides;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidesRepository extends JpaRepository<Partides, Integer> {
    
    List<Partides> findByPartidaFinalitzada(boolean partidaFinalitzada);
}
