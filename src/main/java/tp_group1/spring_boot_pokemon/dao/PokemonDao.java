package tp_group1.spring_boot_pokemon.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import tp_group1.spring_boot_pokemon.model.Pokemon;

import java.util.List;

@Repository
public interface PokemonDao extends CrudRepository<Pokemon, Long> {
    //trouver tous les pokemons
    List<Pokemon> findAll();

    //trouver tous les pokemons par nom par ordre alphabétique
    List<Pokemon> findAllByOrderByName();

    //trouver un pokemon par nom, par une partie du nom en paramètre et ne tenant pas compte de la casse
    List<Pokemon> findByNameContainingIgnoringCase(String name);

    //trouver tous les pokemons par id
    List<Pokemon> findAllById(Iterable<Long> ids);

    //find by Trainer id
    List<Pokemon> findByTrainerId(Long id);

    // Trouver les Pokémon par ID de l'espèce
    List<Pokemon> findBySpeciesId(Long id);

    //trouver les Pokemons par id de l'attaque
    List<Pokemon> findByAttacksId(Long id);

}
