package tp_group1.spring_boot_pokemon.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import tp_group1.spring_boot_pokemon.model.Species;

@Repository
public interface SpeciesDao extends CrudRepository<Species, Long> {

}
