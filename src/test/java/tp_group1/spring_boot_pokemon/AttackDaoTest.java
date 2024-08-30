package tp_group1.spring_boot_pokemon;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tp_group1.spring_boot_pokemon.dao.AttackDao;
import tp_group1.spring_boot_pokemon.dao.PokemonDao;
import tp_group1.spring_boot_pokemon.dao.SpeciesDao;
import tp_group1.spring_boot_pokemon.model.Attack;
import tp_group1.spring_boot_pokemon.model.Pokemon;
import tp_group1.spring_boot_pokemon.model.Species;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
public class AttackDaoTest {
    @Autowired
    private AttackDao attackDao;
    @Autowired
    private SpeciesDao speciesDao;
    @Autowired
    private PokemonDao pokemonDao;

    private Attack attack1;
    private Attack attack2;
    private Attack attack3;
    private Attack attack4;

    @BeforeEach
    public void setUp() {
        attack1 = new Attack(null, "green attack", "water", 10, null, null);
        attack2 = new Attack(null, "fireball", "fire", 34, null, null);
        attack3 = new Attack(null, "thunder strike", "electric", 104, null, null);
        attack4 = new Attack(null, "rock throw", "rock", 5, null, null);

        attackDao.save(attack1);
        attackDao.save(attack2);
        attackDao.save(attack3);
        attackDao.save(attack4);
    }

    @AfterEach
    public void tearDown() {
        attackDao.deleteAll();
    }

    @Test
    public void testFindAttackById() {
        Attack attackSaved = attackDao.save(attack1);
        Assertions.assertEquals(attack1.getId(), attackSaved.getId());
        Assertions.assertEquals(attack1.getAttackName(), attackSaved.getAttackName());

    }

    @Test
    public void testFindAllAttacks() {
        List<Attack> attacks = attackDao.findAll();
        Assertions.assertEquals(4, attacks.size());

    }

    @Test
    public void testDeleteAttackById() {
        //save and verify attack2
        Attack attackSaved = attackDao.save(attack2);
        Assertions.assertNotNull(attackSaved);
        Assertions.assertEquals(attack2.getId(), attackSaved.getId());
        Assertions.assertEquals(attack2.getAttackName(), attackSaved.getAttackName());
        //delete attack by id
        attackDao.deleteById(attackSaved.getId());
        //verify attack deleted
        Optional<Attack> deletedAttack = attackDao.findById(attackSaved.getId());
        Assertions.assertTrue(deletedAttack.isEmpty());
    }

    @Test
    public void testFindAllByOrderByAttackNameAsc() {
        List<Attack> attacksAsc = attackDao.findAllByOrderByAttackNameAsc();
        Assertions.assertEquals("fireball", attacksAsc.get(0).getAttackName());
    }

    @Test
    public void testFindByNameContainingIgnoringCase() {
        List<Attack> foundAttacks = attackDao.findByAttackNameContainingIgnoringCase("unDe");
        Assertions.assertEquals(1, foundAttacks.size());
        Assertions.assertEquals("thunder strike", foundAttacks.get(0).getAttackName());
    }

    @Test
    public void testFindAttackBySpeciesId() {
        Species espece1 = new Species(null, "Dragon", "feu", 3, null, null);
        speciesDao.save(espece1);
        Attack attack5 = new Attack(null, "fireball", "fire", 34, null, null);
        attack5.setSpecies(espece1);
        attackDao.save(attack5);

        Assertions.assertNotNull(attack5.getSpecies().getId());
        Assertions.assertEquals(espece1.getId(), attack5.getSpecies().getId());

        List<Attack> attackBySpecies = attackDao.findBySpeciesId(espece1.getId());
        Assertions.assertNotNull(attackBySpecies);
        Assertions.assertEquals(1, attackBySpecies.size());
        Assertions.assertEquals(espece1.getId(), attackBySpecies.get(0).getSpecies().getId());

    }

    @Test
    public void testFindAttackByPokemonsId() {
        Pokemon pokemon11 = new Pokemon(null, "Pikachu", 1, 120, 35, 35, null, null, null);
        pokemonDao.save(pokemon11);
        Attack attack6 = new Attack(null, "fireball", "fire", 34, null, null);
        Set<Pokemon> pokemons = new HashSet<>();
        pokemons.add(pokemon11);
        attack6.setPokemons(pokemons);
        Attack attackSaved = attackDao.save(attack6);
        Assertions.assertNotNull(attackSaved.getId());

        Attack attackPlaned = attackDao.findById(attackSaved.getId()).orElse(null);
        Assertions.assertNotNull(attackPlaned);
        Assertions.assertNotNull(attackPlaned.getPokemons());

    }

}
