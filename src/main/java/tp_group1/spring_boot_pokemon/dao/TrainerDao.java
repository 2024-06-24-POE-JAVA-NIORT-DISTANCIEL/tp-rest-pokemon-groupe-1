package tp_group1.spring_boot_pokemon.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tp_group1.spring_boot_pokemon.model.Trainer;

import java.util.List;

@Repository
public interface TrainerDao extends CrudRepository<Trainer, Long> {

    List<Trainer> findAll();

    List<Trainer> findByUsernameContainingIgnoringCase(String username);

    List<Trainer> findAllByOrderByUsernameAsc();

//  List<Trainer> findAllByOrderByUsernameDesc();

//    @Query("SELECT t FROM Trainer t JOIN t.pokemons p WHERE p.name = :name")
    List<Trainer> findByPokemonName(String name);





}
