package SpringBANG.services;

import SpringBANG.entity.Partides;
import SpringBANG.repository.PartidesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidesService {
    @Autowired
    PartidesRepository repositori;

    public Partides findById(Integer id) {
        return repositori.findById(id).orElse(null);
    }

    public List<Partides> findAll() {
        return repositori.findAll();
    }

    public void delete(Integer id) {

        repositori.deleteById(id);
    }

    public void deleteAll() {

        repositori.deleteAll();
    }

    public Partides insertar(Partides j) {
        return repositori.save(j);
    }

    public Partides editar(Partides j) {
        return repositori.save(j);
    }

    public List<Partides> getPartidesNoFinalitzades() {
        return repositori.findByPartidaFinalitzada(false);
    }

    public List<Partides> getPartidesFinalitzades() {
        return repositori.findByPartidaFinalitzada(true);
    }
}
