package tp_group1.spring_boot_pokemon.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Indexed;
import tp_group1.spring_boot_pokemon.model.Trainer;

@Indexed
public interface TrainerDao extends CrudRepository<Trainer, Long> {

}
