package tp_group1.spring_boot_pokemon.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tp_group1.spring_boot_pokemon.dao.TrainerDao;
import tp_group1.spring_boot_pokemon.model.Trainer;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {
    @Autowired
    private TrainerDao trainerDao;

    //methode save
    @Transactional
    public Trainer save(Trainer trainer) {
        return trainerDao.save(trainer);
    }

    //methode find by
    public Optional<Trainer> findById(Long id) {
       return trainerDao.findById(id);
    }

    //methode pour trouver tous les trainers
    public List<Trainer> findAll() {
        return trainerDao.findAll();
    }

    //delete by id
    @Transactional
    public void deleteById(Long id) {
        trainerDao.deleteById(id);
    }

}
