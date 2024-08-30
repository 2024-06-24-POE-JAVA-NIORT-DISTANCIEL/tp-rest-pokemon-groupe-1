package tp_group1.spring_boot_pokemon.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import tp_group1.spring_boot_pokemon.model.Species;

import java.util.List;

@Repository
public interface SpeciesDao extends CrudRepository<Species, Long> {
    // Find all species
    List<Species> findAll();

    // Find species by name
    List<Species> findByNameContainingIgnoreCase(String name);

    // Find species by Pokemon ID
    List<Species> findByPokemonsId(Long pokemonId);

    // Find species by Attack ID
    List<Species> findByAttacksId(Long id);

}

