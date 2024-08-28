package tp_group1.spring_boot_pokemon.dao;

import org.springframework.data.repository.CrudRepository;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;
import tp_group1.spring_boot_pokemon.model.Trainer;

import java.util.List;

@Repository
public interface TrainerDao extends CrudRepository<Trainer, Long> {
    List<Trainer> findByUsernameContainingIgnoringCase(String username);
    List<Trainer> findAllByOrderByUsernameAsc();
    List<Trainer> findAllByOrderByUsernameDesc();
=======
import org.springframework.stereotype.Indexed;
import tp_group1.spring_boot_pokemon.model.Trainer;

@Indexed
public interface TrainerDao extends CrudRepository<Trainer, Long> {

>>>>>>> 741f41c3892409a9035325026481678a53726732
}
