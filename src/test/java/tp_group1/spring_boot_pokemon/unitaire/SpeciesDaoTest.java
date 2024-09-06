package tp_group1.spring_boot_pokemon.unitaire;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tp_group1.spring_boot_pokemon.dao.SpeciesDao;
import tp_group1.spring_boot_pokemon.dao.PokemonDao;
import tp_group1.spring_boot_pokemon.dao.AttackDao;
import tp_group1.spring_boot_pokemon.model.Attack;
import tp_group1.spring_boot_pokemon.model.AttackType;
import tp_group1.spring_boot_pokemon.model.Pokemon;
import tp_group1.spring_boot_pokemon.model.Species;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class SpeciesDaoTest {

    @Autowired
    private SpeciesDao speciesDao;

    @Autowired
    private PokemonDao pokemonDao;

    @Autowired
    private AttackDao attackDao;

    private Species testSpecies;
    private Pokemon testPokemon;
    private Attack testAttack;

    @BeforeEach
    public void setUp() {
        testSpecies = new Species(null, "TestSpecies", "TestType", 50, null, null);
        speciesDao.save(testSpecies);

        // Assuming Pokemon and Attack classes are correctly mapped
        testPokemon = new Pokemon(null, "TestPokemon", 5, 120, 35, 35, null, testSpecies, null);
        pokemonDao.save(testPokemon);
        testAttack = new Attack(null, "TestAttack", AttackType.INSECTE, 30, null, null);
        attackDao.save(testAttack);
    }

    @Test
    public void testFindBySpecieNameContainingIgnoreCase() {
        List<Species> foundSpecies = speciesDao.findBySpecieNameContainingIgnoreCase("testspecies");
        assertFalse(foundSpecies.isEmpty(), "La liste des species ne doit pas être vide");
        assertEquals("TestSpecies", foundSpecies.get(0).getSpecieName(), "Le nom de la species doit être 'TestSpecies'");
    }

    @Test
    public void testFindAll() {
        List<Species> speciesList = speciesDao.findAll();
        assertEquals(1, speciesList.size(), "Il doit y avoir une seule species dans la base de données");
    }

    @Test
    public void testFindSpeciesById() {
        Optional<Species> foundSpecies = speciesDao.findById(testSpecies.getId());
        assertTrue(foundSpecies.isPresent(), "La species doit être trouvée par ID");
        assertEquals(testSpecies.getSpecieName(), foundSpecies.get().getSpecieName(), "Le nom de la species doit correspondre");
    }

    @Test
    public void testFindSpeciesByPokemonId() {
        speciesDao.save(testSpecies);  // Sauvegarde la species
        List<Species> foundSpecies = speciesDao.findByPokemonsId(testPokemon.getId());
        assertFalse(foundSpecies.isEmpty(), "La species doit être trouvée par Pokemon ID");
        assertEquals(testSpecies.getSpecieName(), foundSpecies.get(0).getSpecieName(), "Le nom de la species doit correspondre");
    }

    @Test
    public void testFindSpeciesByAttackId() {
        speciesDao.save(testSpecies);  // Sauvegarde la species
        List<Species> foundSpecies = speciesDao.findByAttackId(testAttack.getId());
        assertFalse(foundSpecies.isEmpty(), "La species doit être trouvée par Attack ID");
        assertEquals(testSpecies.getSpecieName(), foundSpecies.get(0).getSpecieName(), "Le nom de la species doit correspondre");
    }

    @Test
    public void testDeleteById() {
        speciesDao.deleteById(testSpecies.getId());
        assertFalse(speciesDao.findById(testSpecies.getId()).isPresent(), "La species doit être supprimée");
    }

    @Test
    public void testDeleteSpeciesByIdFailure() {
        // Charge la species pour s'assurer qu'elle est bien liée au Pokemon
        Species speciesWithPokemon = speciesDao.findById(testSpecies.getId()).orElse(null);
        assertNotNull(speciesWithPokemon, "La species doit être trouvée dans la base de données");

        // Essayez de supprimer la species, ce qui devrait échouer
        try {
            speciesDao.deleteById(speciesWithPokemon.getId());
            fail("La suppression devrait échouer si la species a des Pokemons associés");
        } catch (Exception e) {
            assertTrue(e instanceof RuntimeException, "Une exception RuntimeException doit être levée");
            assertEquals("Cannot delete species with associated Pokemons", e.getMessage(), "Le message de l'exception doit être correct");
        }
    }
}
