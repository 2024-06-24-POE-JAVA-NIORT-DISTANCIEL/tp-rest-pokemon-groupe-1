package tp_group1.spring_boot_pokemon.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import tp_group1.spring_boot_pokemon.model.Species;

import java.util.List;

@Repository
public interface SpeciesDao extends CrudRepository<Species, Long> {
    // trouver toutes les espèces
    List<Species> findAll();

    // trouver une espèce par son nom, une partie de son nom, sans prendre en compte la casse
    List<Species> findBySpecieNameContainingIgnoreCase(String specieName);

    // trouver une espèce par le pokemon id
    List<Species> findByPokemonsId(Long id);

    // trouver une espèece par l'attaque id
    List<Species> findByAttackId(Long id);

}
