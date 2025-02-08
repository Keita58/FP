package SpringBANG.services;

import SpringBANG.entity.Personatges;
import SpringBANG.repository.PersonatgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonatgeService {
    @Autowired
    private PersonatgeRepository repositori;


    public Personatges findById(Integer id) {
        return repositori.findById(id).orElse(null);
    }

    public List<Personatges> findAll() {
        return repositori.findAll();
    }

    public void delete(Integer id) {

        repositori.deleteById(id);
    }
    public void deleteAll() {

        repositori.deleteAll();
    }
    public Personatges insertar(Personatges j) {
        return repositori.save(j);
    }
    public Personatges editar(Personatges j) {
        return repositori.save(j);
    }


}
