package SpringBANG.services;

import SpringBANG.entity.Armes;
import SpringBANG.repository.ArmesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArmesService {

    @Autowired
    ArmesRepository armesRepository;

    public List<Armes> findAll() {
        return  armesRepository.findAll();
    }

    public Armes findById(int id) {
        return armesRepository.findById(id).orElse(null);
    }
    public void delete(int id) {
        armesRepository.deleteById(id);
    }
    public Armes insert(Armes armes) {
        return armesRepository.save(armes);
    }

    public void deleteAll() {
        armesRepository.deleteAll();
    }

}
