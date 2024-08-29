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
        Trainer trainerSaved = this.trainerDao.save(trainer);
        return trainerSaved;
    }

    //methode find by
    public Trainer findById(Long id) {
        Optional<Trainer> optionalTrainer = this.trainerDao.findById(id);
        return optionalTrainer.orElse(null);
    }

    //methode pour trouver tous les trainers
    public List<Trainer> findAll() {
        return (List<Trainer>) this.trainerDao.findAll();
    }

    //delete by id
    @Transactional
    public void deleteById(Long id) {
        this.trainerDao.deleteById(id);
    }

}
