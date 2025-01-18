package SpringBANG.services;

import SpringBANG.entity.JugadorsRivals;
import SpringBANG.repository.JugadorsRivalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JugadorsRivalsService {
    @Autowired
    JugadorsRivalsRepository repositori;


    public JugadorsRivals findById(Integer id) {
        return repositori.findById(id).orElse(null);
    }

    public List<JugadorsRivals> findAll() {
        return repositori.findAll();
    }

    public void delete(Integer id) {

        repositori.deleteById(id);
    }

    public void deleteAll() {
        repositori.deleteAll();
    }

    public JugadorsRivals insertar(JugadorsRivals j) {
        return repositori.save(j);
    }

    public JugadorsRivals editar(JugadorsRivals j) {
        return repositori.save(j);
    }
}
