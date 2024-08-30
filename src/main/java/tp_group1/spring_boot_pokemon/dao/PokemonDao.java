package tp_group1.spring_boot_pokemon.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import tp_group1.spring_boot_pokemon.model.Pokemon;

import java.util.List;

@Repository
public interface PokemonDao extends CrudRepository<Pokemon, Long> {
    //find all Pokemons
    List<Pokemon> findAll();

    //find all Pokemons by Name Asc
    List<Pokemon> findAllByOrderByName();

    //find a pokemon By Name
    List<Pokemon> findByNameContainingIgnoringCase(String name);

    //find by Trainer id
    List<Pokemon> findByTrainerId(Long id);

    // Trouver les Pokémon par ID de l'espèce
    List<Pokemon> findBySpeciesId(Long speciesId);

}
