package tp_group1.spring_boot_pokemon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tp_group1.spring_boot_pokemon.dao.FightDao;
import tp_group1.spring_boot_pokemon.dao.PokemonDao;
import tp_group1.spring_boot_pokemon.model.Fight;
import tp_group1.spring_boot_pokemon.model.Pokemon;

import java.util.Optional;

@SpringBootTest
public class FightDaoTest {
    @Autowired
    private FightDao fightDao;
    @Autowired
    private PokemonDao pokemonDao;

    @Test
    public void testSaveAndFindByIdAFight() {
        // créer et sauvegarder deux pokemons
        Pokemon pokemonA = new Pokemon();
        pokemonA.setName("Pikachu");
        pokemonDao.save(pokemonA);
        Pokemon pokemonB = new Pokemon();
        pokemonB.setName("Charmander");
        pokemonDao.save(pokemonB);

        // créer et sauvegarder un combat
        Fight fight = new Fight();
        fight.setPokemonA(pokemonA);
        fight.setPokemonB(pokemonB);
        fight.setResult("Victory for Pikachu");
        fightDao.save(fight);
        // récupérer le combat par son id
        Fight savedFight = fightDao.findById(fight.getId()).orElse(null);

        // vérifications
        Assertions.assertNotNull(savedFight);
        Assertions.assertEquals(pokemonA.getName(), fight.getPokemonA().getName());
        Assertions.assertEquals(pokemonB.getName(), fight.getPokemonB().getName());
    }

    @Test
    public void testDeleteFightById() {
        // créer et sauvegarder deux pokemons
        Pokemon pokemonA = new Pokemon();
        pokemonA.setName("Pikachu");
        pokemonDao.save(pokemonA);
        Pokemon pokemonB = new Pokemon();
        pokemonB.setName("Charmander");
        pokemonDao.save(pokemonB);

        // créer et sauvegarder un combat
        Fight fight = new Fight();
        fight.setPokemonA(pokemonA);
        fight.setPokemonB(pokemonB);
        fight.setResult("Victory for Pikachu");
        fightDao.save(fight);
        // récupérer le combat par son id
        Fight savedFight = fightDao.findById(fight.getId()).orElse(null);

        //supprimer le combat par son id
        fightDao.deleteById(savedFight.getId());
        Optional<Fight> deletedFight = fightDao.findById(savedFight.getId());
        Assertions.assertTrue(deletedFight.isEmpty());
    }
}
