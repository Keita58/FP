package SpringBANG.services;

import SpringBANG.entity.Rol;
import SpringBANG.entity.Rols;
import SpringBANG.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RolService {
    @Autowired
    private RolRepository repositori;


    public Rols findById(Integer id) {
        return repositori.findById(id).orElse(null);
    }

    public List<Rols> findAll() {
        return repositori.findAll();
    }

    public void delete(Integer id) {

        repositori.deleteById(id);
    }

    public void deleteAll() {

        repositori.deleteAll();
    }

    public Rols insertar(Rols j) {
        return repositori.save(j);
    }

    public Rols editar(Rols j) {
        return repositori.save(j);
    }

    public Rols buscarRol(Rol rol) {
        return repositori.findByNomRol(rol);
    }
}
