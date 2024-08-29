package tp_group1.spring_boot_pokemon;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tp_group1.spring_boot_pokemon.dao.PokemonDao;
import tp_group1.spring_boot_pokemon.dao.TrainerDao;
import tp_group1.spring_boot_pokemon.model.Pokemon;
import tp_group1.spring_boot_pokemon.model.Trainer;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PokemonDaoTest {
    @Autowired
    private PokemonDao pokemonDao;
    @Autowired
    private TrainerDao trainerDao;

    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private Pokemon pokemon3;
    private Pokemon pokemon4;
    private Pokemon pokemon5;
    private Pokemon pokemon6;
    private Pokemon pokemon7;
    private Pokemon pokemon8;
    private Pokemon pokemon9;
    private Pokemon pokemon10;

    @BeforeEach
    public void setUp() {
        pokemon1 = new Pokemon(null, "Pikachu", 5, 120, 35, 35, null);
        pokemon2 = new Pokemon(null, "Charmander", 8, 240, 39, 39, null);
        pokemon3 = new Pokemon(null, "Bulbasaur", 7, 180, 45, 45, null);
        pokemon4 = new Pokemon(null, "Squirtle", 6, 160, 44, 44, null);
        pokemon5 = new Pokemon(null, "Jigglypuff", 3, 90, 115, 115, null);
        pokemon6 = new Pokemon(null, "Meowth", 4, 110, 40, 40, null);
        pokemon7 = new Pokemon(null, "Psyduck", 6, 160, 50, 50, null);
        pokemon8 = new Pokemon(null, "Machop", 9, 270, 70, 70, null);
        pokemon9 = new Pokemon(null, "Geodude", 7, 210, 40, 40, null);
        pokemon10 = new Pokemon(null, "Eevee", 5, 130, 55, 55, null);

        pokemonDao.save(pokemon1);
        pokemonDao.save(pokemon2);
        pokemonDao.save(pokemon3);
        pokemonDao.save(pokemon4);
        pokemonDao.save(pokemon5);
        pokemonDao.save(pokemon6);
        pokemonDao.save(pokemon7);
        pokemonDao.save(pokemon8);
        pokemonDao.save(pokemon9);
        pokemonDao.save(pokemon10);
    }

    @AfterEach
    public void tearDown() {
        pokemonDao.deleteAll();
    }

    @Test
    public void testFindPokemonById() {
        Pokemon pokemonSaved = pokemonDao.save(pokemon1);
        Assertions.assertEquals(pokemon1.getId(), pokemonSaved.getId());
        Assertions.assertEquals(pokemon1.getName(), pokemonSaved.getName());
    }

    @Test
    public void testDeletePokemonById() {
        // Save and verify pokemon1
        Pokemon pokemonSaved = pokemonDao.save(pokemon2);
        Assertions.assertNotNull(pokemonSaved);
        Assertions.assertEquals(pokemon2.getId(), pokemonSaved.getId());
        Assertions.assertEquals(pokemon2.getName(), pokemonSaved.getName());
        // Delete pokemon1 by its ID
        pokemonDao.deleteById(pokemonSaved.getId());
        // Verify deletion
        Optional<Pokemon> deletedPokemon = pokemonDao.findById(pokemonSaved.getId());
        Assertions.assertTrue(deletedPokemon.isEmpty());
    }

    @Test
    public void testFindAllPokemons() {
        List<Pokemon> pokemons = pokemonDao.findAll();
        Assertions.assertEquals(10, pokemons.size());
    }

    @Test
    public void testFindAllByOrderByNameAsc() {
        List<Pokemon> pokemonsAsc = pokemonDao.findAllByOrderByName();
        //verify order asc
        Assertions.assertEquals("Bulbasaur", pokemonsAsc.get(0).getName());
        Assertions.assertEquals("Squirtle", pokemonsAsc.get(9).getName());
    }

    @Test
    public void testFindByNameContainingIgnoringCase() {
        List<Pokemon> foundPokemons = pokemonDao.findByNameContainingIgnoringCase("PiKa");
        Assertions.assertEquals(1, foundPokemons.size());
        Assertions.assertEquals("Pikachu", foundPokemons.get(0).getName());
    }

    @Test
    public void testFindByTrainerId() {
        // Create a new trainer
        Trainer trainer1 = new Trainer(1L, "Gunther", "12AZ", 100, null, null);
        trainerDao.save(trainer1);

        // Assign the trainer to pokemon1
        pokemon1.setTrainer(trainer1);
        pokemonDao.save(pokemon1);

        // Find Pokemon by trainer ID
        List<Pokemon> foundPokemon = pokemonDao.findByTrainerId(trainer1.getId());

        // Assertions
        Assertions.assertNotNull(foundPokemon);
        Assertions.assertFalse(foundPokemon.isEmpty());
        Assertions.assertEquals(1, foundPokemon.size());
        Assertions.assertEquals(trainer1.getId(), foundPokemon.get(0).getTrainer().getId());
    }

}