package SpringBANG.services;

import SpringBANG.repository.TipusCartesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import SpringBANG.entity.TipusCartes;

@Service
public class TipusCartesService {
    @Autowired
    private TipusCartesRepository repositori;

    public TipusCartes findById(Integer id) {
        return repositori.findById(id).orElse(null);
    }
    
    public List<TipusCartes> findAll() {
        return repositori.findAll();
    }

    public void delete(Integer id) {

        repositori.deleteById(id);
    }
    public void deleteAll() {

        repositori.deleteAll();
    }
    public TipusCartes insertar(TipusCartes j) {
        return repositori.save(j);
    }
    public TipusCartes editar(TipusCartes j) {
        return repositori.save(j);
    }

}
