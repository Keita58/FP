package SpringBANG.repository;

import SpringBANG.entity.Personatges;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonatgeRepository extends JpaRepository<Personatges, Integer> {
}
