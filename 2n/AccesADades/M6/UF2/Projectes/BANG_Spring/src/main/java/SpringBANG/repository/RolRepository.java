package SpringBANG.repository;

import SpringBANG.entity.Rol;
import SpringBANG.entity.Rols;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rols, Integer> {
    Rols findByNomRol(Rol nomRol);
}
